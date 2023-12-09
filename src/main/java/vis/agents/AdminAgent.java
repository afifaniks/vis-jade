package vis.agents;

import com.google.gson.Gson;
import jade.content.AgentAction;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.constants.AgentIdentifier;
import vis.dto.request.*;
import vis.ontology.VISOntology;
import vis.ontology.actions.*;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.Subscription;
import vis.ontology.concepts.User;
import vis.ontology.concepts.Vehicle;
import vis.ontology.predicates.*;
import vis.services.schema.AgentOperationStatusSchema;

import java.io.IOException;
import java.util.Objects;

/***
 * Represents the AdminAgent; the orchestrator or the manager of all the other agents.
 * Every request from the user initially is processed by the AdminAgent. By the contents
 * of the request AdminAgent decides which other agent it should use to get the job done.
 * The inter-agent communications are done by ACL messaging. After passing down the
 * request to another user, this agent waits to receive a response and when the response
 * is received it returns the response to the backend controller.
 */
public class AdminAgent extends Agent {

	private final Gson gson = new Gson();

	private final Codec codec = new SLCodec();

	private final Ontology ontology = VISOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(ontology.getName()));

	private final Logger logger = LoggerFactory.getLogger(AdminAgent.class);

	@Override
	protected void setup() {
		logger.info("Admin agent started. AID: " + getAID().getName());
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);

		addBehaviour(new CyclicBehaviour() {
			/***
			 * By design, the action method defined here intercepts the messages sent
			 * through JadeGateway from the controller. It expects to receive an
			 * AgentActionIdentifier object that defines the target agent and the job that
			 * needs to be done.
			 */
			@Override
			public void action() {
				ACLMessage receivedMessage = null;
				receivedMessage = blockingReceive();
				AgentActionIdentifier request = null;
				try {
					request = (AgentActionIdentifier) receivedMessage.getContentObject();
				}
				catch (UnreadableException e) {
					throw new RuntimeException(e);
				}
				logger.info("Request received from gateway handler: " + request);

				String response = null;
				try {
					response = relayRequest(request);

				}
				catch (IOException | Codec.CodecException | OntologyException e) {
					throw new RuntimeException(e);
				}

				ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
				responseMessage.addReceiver(receivedMessage.getSender());
				responseMessage.setContent(response);
				myAgent.send(responseMessage);
			}

			/***
			 * This method unpacks the AgentActionIdentifier object and transmits the
			 * request to the other agent for further processing.
			 * @param request The request object defining the request contents.
			 * @throws IOException If there is any issue with communicating other agents.
			 * @throws OntologyException If the response lies outside the defined
			 * ontology.
			 * @throws Codec.CodecException If there is any issue with the CODEC.
			 */
			private String relayRequest(AgentActionIdentifier request)
					throws IOException, OntologyException, Codec.CodecException {
				ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
				AgentAction action = null;
				message.setLanguage(codec.getName());
				message.setOntology(ontology.getName());
				message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

				if (Objects.equals(request.getTargetAgent(), AgentIdentifier.AUTHENTICATION)) {
					message.addReceiver(new AID(AgentIdentifier.AUTHENTICATION, AID.ISLOCALNAME));
					message.setContentObject(request);
				}
				else if (Objects.equals(request.getTargetAgent(), AgentIdentifier.INSURANCE_CLAIM)) {
					ClaimRequest packageRequest = gson.fromJson(request.getContents(), ClaimRequest.class);
					User user = new User();
					user.setEmail(packageRequest.getUserEmail());
					action = new ClaimInsurance(user, new Subscription(packageRequest.getSubscriptionId()));
					message.addReceiver(new AID(AgentIdentifier.INSURANCE_CLAIM, AID.ISLOCALNAME));
					myAgent.getContentManager()
						.fillContent(message,
								new Action(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME), action));
				}
				else if (Objects.equals(request.getTargetAgent(), AgentIdentifier.CUSTOMER_ASSISTANT)) {
					message.addReceiver(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME));

					if (request.getAction().equals("subscribe")) {
						SubscribePackageRequest packageRequest = gson.fromJson(request.getContents(),
								SubscribePackageRequest.class);
						User user = new User();
						user.setEmail(packageRequest.getUserEmail());
						action = new SubscribePackage(new InsurancePackage(packageRequest.getPackageId()), user,
								new Vehicle(packageRequest.getVehicleId(), packageRequest.getUserEmail()));
					}
					if (request.getAction().equals("vehicle-registration")) {
						VehicleRegistrationRequest vehicleRegistrationRequest = gson.fromJson(request.getContents(),
								VehicleRegistrationRequest.class);
						action = new VehicleRegistration(new User("", vehicleRegistrationRequest.getUserEmail()),
								new Vehicle(vehicleRegistrationRequest.getUserEmail(),
										vehicleRegistrationRequest.getVehicleName(),
										vehicleRegistrationRequest.getVehicleModel(),
										vehicleRegistrationRequest.getVehicleType(),
										vehicleRegistrationRequest.getLicenseNumber(),
										vehicleRegistrationRequest.getVehicleRegistrationNumber(),
										vehicleRegistrationRequest.getPurchaseDate(),
										vehicleRegistrationRequest.getVehicleStatus(),
										vehicleRegistrationRequest.getMileage(),
										vehicleRegistrationRequest.getDrivingLicense(),
										vehicleRegistrationRequest.getLicenseDateOfExpiry()));
					}
					if (request.getAction().equals("get-package")) {
						PackageRecommendationRequest packageRequest = gson.fromJson(request.getContents(),
								PackageRecommendationRequest.class);
						User user = new User();
						user.setEmail(packageRequest.getUserEmail());
						action = new PackageRecommendation(user,
								new Vehicle(packageRequest.getVehicleId(), packageRequest.getUserEmail()));
					}

					if (request.getAction().equals("get-user")) {
						UserRequest userRequest = gson.fromJson(request.getContents(), UserRequest.class);
						User user = new User();
						user.setEmail(userRequest.getEmail());
						action = new GetUser(user);
					}

					myAgent.getContentManager()
						.fillContent(message,
								new Action(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME), action));

				}

				send(message);
				ACLMessage receivedMessage = blockingReceive(messageTemplate);

				return responseProcessor(receivedMessage);
			}

			/***
			 * This method is responsible to process the response received from the other
			 * agents when an assigned job is done. This message expects to receive a
			 * Predicate defined in the VISOntology to assert the response status and
			 * sends back the result to the calling method.
			 * @param responseMessage A response message in ACLMessage format containing
			 * the results of the request.
			 * @throws OntologyException If the response lies outside the defined
			 * ontology.
			 * @throws Codec.CodecException If there is any issue with the CODEC.
			 */
			private String responseProcessor(ACLMessage responseMessage)
					throws OntologyException, Codec.CodecException {
				ContentElement contentElement = myAgent.getContentManager().extractContent(responseMessage);

				if (contentElement instanceof SubscriptionSuccess) {
					return gson.toJson(new AgentOperationStatusSchema(200, "Subscription successful."));
				}
				else if (contentElement instanceof VehicleRegistrationSuccess) {
					return gson.toJson(new AgentOperationStatusSchema(200, "Vehicle registration successful."));
				}
				else if (contentElement instanceof Recommendation recommendation) {
					return gson.toJson(new AgentOperationStatusSchema(200, gson.toJson(recommendation.getPackages())));
				}
				else if (contentElement instanceof ClaimSuccess claimSuccess) {
					return gson.toJson(new AgentOperationStatusSchema(200,
							"Claim successful for user: " + claimSuccess.getUser().getEmail()));
				}
				else if (contentElement instanceof SignupSuccess signupSuccess) {
					return gson
						.toJson(new AgentOperationStatusSchema(signupSuccess.getStatus(), signupSuccess.getMessage()));
				}
				else if (contentElement instanceof LoginSuccess loginSuccess) {
					return gson.toJson(new AgentOperationStatusSchema(200, gson.toJson(loginSuccess)));
				}
				else if (contentElement instanceof GetUserSuccess getUserSuccess) {
					return gson.toJson(new AgentOperationStatusSchema(200, gson.toJson(getUserSuccess.getUser())));
				}
				else if (contentElement instanceof SystemError systemError) {
					return gson
						.toJson(new AgentOperationStatusSchema(systemError.getStatus(), systemError.getMessage()));
				}

				return null;
			}
		});

	}

}
