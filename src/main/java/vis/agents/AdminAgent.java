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
import vis.dto.request.SubscribePackageRequest;
import vis.ontology.PackageSubscriptionOntology;
import vis.ontology.actions.SubscribePackage;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.User;
import vis.ontology.predicates.SubscriptionSuccess;
import vis.ontology.predicates.SystemError;
import vis.ontology.predicates.Vehicle;
import vis.services.schema.AgentOperationStatus;

import java.io.IOException;
import java.util.Objects;

public class AdminAgent extends Agent {

	private Gson gson = new Gson();

	private Codec codec = new SLCodec();

	private Ontology packageSubscriptionOntology = PackageSubscriptionOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(packageSubscriptionOntology.getName()));

	private final Logger logger = LoggerFactory.getLogger(AdminAgent.class);

	@Override
	protected void setup() {
		logger.debug("Admin agent started. AID: " + getAID().getName());
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(packageSubscriptionOntology);

		addBehaviour(new CyclicBehaviour() {
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

			private String relayRequest(AgentActionIdentifier request)
					throws IOException, OntologyException, Codec.CodecException {
				ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
				message.setLanguage(codec.getName());
				message.setOntology(packageSubscriptionOntology.getName());
				message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

				if (Objects.equals(request.getTargetAgent(), AgentIdentifier.AUTHENTICATION)) {
					message.addReceiver(new AID(AgentIdentifier.AUTHENTICATION, AID.ISLOCALNAME));
					message.setContentObject(request);
				}
				else if (Objects.equals(request.getTargetAgent(), AgentIdentifier.CUSTOMER_ASSISTANT)) {
					logger.info("Request received for target agent: " + request.getTargetAgent());
					message.addReceiver(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME));
					AgentAction action = null;

					if (request.getAction().equals("subscribe")) {
						SubscribePackageRequest packageRequest = gson.fromJson(request.getContents(),
								SubscribePackageRequest.class);
						action = new SubscribePackage(new InsurancePackage(packageRequest.getPackageId()),
								new User(packageRequest.getUserId()),
								new Vehicle(packageRequest.getVehicleId(), packageRequest.getUserId()));
					}

					myAgent.getContentManager()
						.fillContent(message,
								new Action(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME), action));

				}

				send(message);
				ACLMessage receivedMessage = blockingReceive(messageTemplate);

				return responseProcessor(request, receivedMessage);
			}

			private String responseProcessor(AgentActionIdentifier request, ACLMessage responseMessage)
					throws OntologyException, Codec.CodecException {
				ContentElement contentElement = myAgent.getContentManager().extractContent(responseMessage);

				if (contentElement instanceof SubscriptionSuccess) {
					return gson.toJson(new AgentOperationStatus(200, "Subscription successful."));
				}
				else if (contentElement instanceof SystemError) {
					return gson.toJson(new AgentOperationStatus(500, "Request failed"));
				}

				return null;
			}
		});

	}

}
