package vis.agents;

import jade.content.onto.basic.Action;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.ontology.PackageSubscriptionOntology;
import vis.ontology.actions.SubscribePackage;
import vis.ontology.concepts.InsurancePackage;
import vis.ontology.concepts.User;
import vis.ontology.concepts.Vehicle;

import java.io.IOException;
import java.util.Objects;

public class AdminAgent extends Agent {

	private Codec codec = new SLCodec();

	private Ontology packageSubscriptionOntology = PackageSubscriptionOntology.getInstance();

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
				catch (IOException e) {
					throw new RuntimeException(e);
				}
				catch (OntologyException e) {
					throw new RuntimeException(e);
				}
				catch (Codec.CodecException e) {
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

				if (Objects.equals(request.getTargetAgent(), AgentIdentifier.AUTHENTICATION)) {
					message.addReceiver(new AID(AgentIdentifier.AUTHENTICATION, AID.ISLOCALNAME));
					message.setContentObject(request);
				}
				else if (Objects.equals(request.getTargetAgent(), AgentIdentifier.CUSTOMER_ASSISTANT)) {
					message.addReceiver(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME));
					message.setLanguage(codec.getName());
					message.setOntology(packageSubscriptionOntology.getName());
					message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

					SubscribePackage subscribePackage = new SubscribePackage(
							new InsurancePackage("testPkgId", "pkgName"), new User("1", "Test"), new Vehicle("2"));

					myAgent.getContentManager()
						.fillContent(message, new Action(new AID(AgentIdentifier.CUSTOMER_ASSISTANT, AID.ISLOCALNAME),
								subscribePackage));

					System.out.println("Sending message");
				}

				send(message);
				ACLMessage receivedMessage = blockingReceive();

				return receivedMessage.getContent();
			}
		});

	}

}
