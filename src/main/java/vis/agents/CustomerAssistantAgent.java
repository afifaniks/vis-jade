package vis.agents;

import com.google.gson.Gson;
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
import vis.dto.response.TokenResponse;
import vis.ontology.PackageSubscriptionOntology;
import vis.ontology.actions.SubscribePackage;

import java.io.IOException;

public class CustomerAssistantAgent extends Agent {

	private final Gson gson = new Gson();

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	@Override
	protected void setup() {
		logger.debug("CustomerAssistant agent started. AID: " + getAID().getName());
        Codec codec = new SLCodec();
        getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL);
        Ontology packageSubscriptionOntology = PackageSubscriptionOntology.getInstance();
        getContentManager().registerOntology(packageSubscriptionOntology);
        MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(packageSubscriptionOntology.getName()));
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				ACLMessage receivedMessage = null;

				receivedMessage = blockingReceive(mt);

				logger.info("Request received from admin agent");

				try {
					if (receivedMessage.getPerformative() == ACLMessage.REQUEST) {
						TokenResponse response = null;

						Action actionContent = (Action) myAgent.getContentManager().extractContent(receivedMessage);
                        SubscribePackage subscribePackage = (SubscribePackage) actionContent.getAction();

                        // TODO: TBD

						// if (ce instanceof SubscribePackage) {
						// logger.info("Matched: " + ce);
						// }

						ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
						responseMessage.addReceiver(receivedMessage.getSender());
						responseMessage.setContentObject(response);
						myAgent.send(responseMessage);
					}
				}
				catch (OntologyException | IOException | Codec.CodecException e) {
					throw new RuntimeException(e);
				}
            }
		});
	}

}
