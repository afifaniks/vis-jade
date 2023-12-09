package vis.agents.behaviour;

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
import vis.agents.AuthenticationAgent;
import vis.constants.AgentIdentifier;
import vis.ontology.VISOntology;
import vis.ontology.actions.GetUser;
import vis.ontology.actions.PackageRecommendation;
import vis.ontology.actions.SubscribePackage;
import vis.ontology.actions.VehicleRegistration;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.predicates.*;
import vis.services.CustomerAssistantService;
import vis.services.CustomerAssistantServiceImpl;
import vis.services.schema.*;

import java.io.IOException;
import java.util.ArrayList;

/***
 * This behavioural class is responsible for serving customer requests. The agent receives
 * requests from the admin agent, and depending on the requested action it serves the
 * request.
 */
public class CustomerAssistantBehaviour extends CyclicBehaviour {

	private final CustomerAssistantService customerAssistantService;

	private final Codec codec = new SLCodec();

	private final Ontology ontology = VISOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(ontology.getName()));

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	public CustomerAssistantBehaviour(Agent agent) {
		super(agent);
		customerAssistantService = new CustomerAssistantServiceImpl(agent);
	}

	/***
	 * This implementation of action method expects a few AgentAction objects defined in
	 * the VIS ontology. Depending on the action it utilizes the associated service to
	 * process the request. Typical tasks include package recommendation, vehicle
	 * registration, subscribing to an insurance package, user profile insights, etc.
	 */
	@Override
	public void action() {
		ACLMessage receivedMessage = null;

		myAgent.getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL);
		myAgent.getContentManager().registerOntology(ontology);
		receivedMessage = myAgent.blockingReceive(messageTemplate);

		logger.info("Request received from admin agent");

		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(receivedMessage.getSender());
		responseMessage.setLanguage(codec.getName());
		responseMessage.setOntology(ontology.getName());

		try {
			if (receivedMessage.getPerformative() == ACLMessage.REQUEST) {

				Action actionContent = (Action) myAgent.getContentManager().extractContent(receivedMessage);

				if (actionContent.getAction() instanceof SubscribePackage) {
					subscribePackage(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof PackageRecommendation) {
					recommendPackages(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof VehicleRegistration) {
					registerVehicle(actionContent, responseMessage);
				}

				if (actionContent.getAction() instanceof GetUser) {
					getUser(actionContent, responseMessage);
				}
			}
		}
		catch (Exception e) {
			logger.error("Error occurred while subscribing package. Sending error response...");
			e.printStackTrace();
		}
	}

	/***
	 * This method intends to subscribe a user to an insurance package.
	 * @param actionContent An instance of SubscribePackage agent action.
	 * @param responseMessage ACLMessage container to be populated and sent back to the
	 * admin agent.
	 * @throws IOException If there is any issue with communicating other agents.
	 * @throws OntologyException If the response lies outside the defined ontology.
	 * @throws Codec.CodecException If there is any issue with the CODEC.
	 */
	private void subscribePackage(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		SubscribePackage subscribePackage = (SubscribePackage) actionContent.getAction();
		SubscriptionRequestSchema subscriptionRequestSchemaRequest = new SubscriptionRequestSchema(
				subscribePackage.getUser().getEmail(), subscribePackage.getVehicle().getVehicleId(),
				subscribePackage.getInsurancePackage().getId());

		SubscriptionStatusSchema subscriptionStatus = customerAssistantService
			.subscribePackage(subscriptionRequestSchemaRequest);

		if (subscriptionStatus.getStatus() == 200) {
			myAgent.getContentManager()
				.fillContent(responseMessage,
						new SubscriptionSuccess(subscribePackage.getInsurancePackage(), subscribePackage.getUser()));
			logger.info("Package subscription successful");
		}
		else {
			SystemError systemError = new SystemError(500, "Subscription error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not subscribe package");
		}

		myAgent.send(responseMessage);
	}

	/***
	 * This method intends to recommend a package based on the vehicle information.
	 * @param actionContent An instance of PackageRecommendation agent action.
	 * @param responseMessage ACLMessage container to be populated and sent back to the
	 * admin agent.
	 * @throws IOException If there is any issue with communicating other agents.
	 * @throws OntologyException If the response lies outside the defined ontology.
	 * @throws Codec.CodecException If there is any issue with the CODEC.
	 */
	private void recommendPackages(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		PackageRecommendation packageRecommendationAction = (PackageRecommendation) actionContent.getAction();
		RecommendationRequestSchema recommendationRequestSchema = new RecommendationRequestSchema(
				packageRecommendationAction.getUser().getEmail(),
				packageRecommendationAction.getVehicle().getVehicleId());

		ArrayList<InsurancePackageSchema> recommendations = customerAssistantService
			.getPackageRecommendation(recommendationRequestSchema);

		if (recommendations != null && !recommendations.isEmpty()) {
			ArrayList<InsurancePackage> packageConcepts = new ArrayList<>();

			for (InsurancePackageSchema pkg : recommendations) {
				packageConcepts.add(new InsurancePackage(pkg.getPackageId(), pkg.getPackageName(),
						pkg.getPackageDescription(), pkg.getPackagePrice(), pkg.getTenure()));
			}
			myAgent.getContentManager()
				.fillContent(responseMessage,
						new Recommendation(packageRecommendationAction.getUser(), packageConcepts));
			logger.info("Packages recommended.");
		}
		else {
			SystemError systemError = new SystemError(500, "Internal error occurred while generating recommendation.");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
		}

		myAgent.send(responseMessage);
	}

	/***
	 * This method intends to register a vehicle to a user's profile.
	 * @param actionContent An instance of VehicleRegistration agent action.
	 * @param responseMessage ACLMessage container to be populated and sent back to the
	 * admin agent.
	 * @throws IOException If there is any issue with communicating other agents.
	 * @throws OntologyException If the response lies outside the defined ontology.
	 * @throws Codec.CodecException If there is any issue with the CODEC.
	 */
	private void registerVehicle(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException, UnreadableException, IOException {
		VehicleRegistration vehicleRegistration = (VehicleRegistration) actionContent.getAction();
		VehicleRegistrationSchema vehicleRegistrationSchema = new VehicleRegistrationSchema(
				vehicleRegistration.getUser().getEmail(), vehicleRegistration.getVehicle().getVehicleName(),
				vehicleRegistration.getVehicle().getVehicleModel(), vehicleRegistration.getVehicle().getVehicleType(),
				vehicleRegistration.getVehicle().getLicenseNumber(),
				vehicleRegistration.getVehicle().getVehicleRegistrationNumber(),
				vehicleRegistration.getVehicle().getPurchaseDate(), vehicleRegistration.getVehicle().getVehicleStatus(),
				vehicleRegistration.getVehicle().getMileage(), vehicleRegistration.getVehicle().getDrivingLicense(),
				vehicleRegistration.getVehicle().getLicenseDateOfExpiry());

		// Verify information
		boolean isVerified = verifyVehicleInformation(vehicleRegistrationSchema);
		logger.info("Information verification status: " + isVerified);

		VehicleRegistrationStatusSchema vehicleRegistrationStatusSchema = customerAssistantService
			.registerVehicle(vehicleRegistrationSchema);

		if (isVerified && vehicleRegistrationStatusSchema.getStatus() == 200) {
			myAgent.getContentManager()
				.fillContent(responseMessage, new VehicleRegistrationSuccess(vehicleRegistration.getUser().getUserId(),
						vehicleRegistration.getVehicle().getVehicleId()));
			logger.info("Vehicle Registration successful");
		}
		else {
			SystemError systemError = new SystemError(500, "Vehicle Registration error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not register vehicle");
		}

		myAgent.send(responseMessage);
	}

	private boolean verifyVehicleInformation(VehicleRegistrationSchema vehicleRegistrationSchema) throws IOException {
		ACLMessage verificationRequest = new ACLMessage(ACLMessage.REQUEST);
		verificationRequest.setContentObject(vehicleRegistrationSchema);
		verificationRequest.addReceiver(new AID(AgentIdentifier.CUSTOMER_VERIFICATION, AID.ISLOCALNAME));
		myAgent.send(verificationRequest);

		ACLMessage verificationResponse = myAgent.blockingReceive();

		return verificationResponse.getPerformative() == ACLMessage.CONFIRM;
	}

	/***
	 * This method intends to get the user profile details. Typical information includes
	 * user credentials, vehicle information and subscribed insurance packages.
	 * @param actionContent An instance of VehicleRegistration agent action.
	 * @param responseMessage ACLMessage container to be populated and sent back to the
	 * admin agent.
	 * @throws IOException If there is any issue with communicating other agents.
	 * @throws OntologyException If the response lies outside the defined ontology.
	 * @throws Codec.CodecException If there is any issue with the CODEC.
	 */
	private void getUser(Action actionContent, ACLMessage responseMessage)
			throws OntologyException, Codec.CodecException, UnreadableException, IOException {
		GetUser getUser = (GetUser) actionContent.getAction();
		GetUserRequestSchema getUserRequestSchema = new GetUserRequestSchema(getUser.getUser().getEmail());

		UserProfileSchema userProfileSchema = customerAssistantService.getUser(getUserRequestSchema);

		if (userProfileSchema != null) {
			myAgent.getContentManager().fillContent(responseMessage, new GetUserSuccess(userProfileSchema));
			logger.info("User Retrieved: " + getUser.getUser());
		}
		else {
			SystemError systemError = new SystemError(500, "User Retrieval error");
			myAgent.getContentManager().fillContent(responseMessage, systemError);
			logger.error("Could not retrieve the user");
		}

		myAgent.send(responseMessage);
	}

}
