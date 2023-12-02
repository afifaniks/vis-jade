package vis.agents.behaviour;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.ontology.PackageSubscriptionOntology;
import vis.ontology.actions.SubscribePackage;
import vis.ontology.predicates.SystemError;
import vis.ontology.predicates.SubscriptionSuccess;
import vis.services.CustomerAssistantService;
import vis.services.CustomerAssistantStatusImpl;
import vis.services.schema.Subscription;
import vis.services.schema.SubscriptionStatus;

public class CustomerAssistantBehaviour extends CyclicBehaviour {

	private final CustomerAssistantService customerAssistantService = new CustomerAssistantStatusImpl();

	private final Codec codec = new SLCodec();

	private final Ontology packageSubscriptionOntology = PackageSubscriptionOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(packageSubscriptionOntology.getName()));

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	public CustomerAssistantBehaviour(Agent agent) {
		super(agent);
	}

	@Override
	public void action() {
		ACLMessage receivedMessage = null;

		myAgent.getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL);
		myAgent.getContentManager().registerOntology(packageSubscriptionOntology);
		receivedMessage = myAgent.blockingReceive(messageTemplate);

		logger.info("Request received from admin agent");

		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(receivedMessage.getSender());
		responseMessage.setLanguage(codec.getName());
		responseMessage.setOntology(packageSubscriptionOntology.getName());

		try {
			if (receivedMessage.getPerformative() == ACLMessage.REQUEST) {

				Action actionContent = (Action) myAgent.getContentManager().extractContent(receivedMessage);

				if (actionContent.getAction() instanceof SubscribePackage) {
					subscribePackage(actionContent, responseMessage);
				}
			}
		}
		catch (Exception e) {
			logger.error("Error occurred while subscribing package. Sending error response...");
			e.printStackTrace();
		}
	}

	private void subscribePackage(Action actionContent, ACLMessage responseMessage)
			throws Codec.CodecException, OntologyException {
		SubscribePackage subscribePackage = (SubscribePackage) actionContent.getAction();
		Subscription subscriptionRequest = new Subscription(subscribePackage.getUser().getUserId(),
				subscribePackage.getVehicle().getVehicleId(), subscribePackage.getInsurancePackage().getPackageId());

		SubscriptionStatus subscriptionStatus = customerAssistantService.subscribePackage(subscriptionRequest);

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

}
