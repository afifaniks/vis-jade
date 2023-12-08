package vis.agents.behaviour;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.ontology.VISOntology;
import vis.ontology.actions.ClaimInsurance;
import vis.ontology.predicates.ClaimSuccess;
import vis.ontology.predicates.SystemError;
import vis.services.InsuranceClaimService;
import vis.services.InsuranceClaimServiceImpl;
import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

/***
 * This behavioural class is associated to the InsuranceClaim agent and helps the customer
 * to claim insurance when required.
 */
public class InsuranceClaimBehaviour extends SimpleBehaviour {

    private final InsuranceClaimService insuranceClaimService;

    private final Codec codec = new SLCodec();

    private final Ontology ontology = VISOntology.getInstance();

    MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()), MessageTemplate.MatchOntology(ontology.getName()));

    private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

    public InsuranceClaimBehaviour(Agent agent) {
        super(agent);
        insuranceClaimService = new InsuranceClaimServiceImpl(agent);
    }

    /***
     * The action method for InsuranceClaimBehaviour straightforwardly approaches to claim
     * the insurance if the customer has an active insurance.
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

                if (actionContent.getAction() instanceof ClaimInsurance) {
                    ClaimInsurance claimInsurance = (ClaimInsurance) actionContent.getAction();
                    InsuranceClaimStatusSchema claimStatus = insuranceClaimService.claimInsurance(new ClaimRequestSchema(claimInsurance.getUser().getEmail(), claimInsurance.getSubscription().getSubscriptionId()));

                    if (claimStatus.getStatus() == 200) {
                        myAgent.getContentManager().fillContent(responseMessage, new ClaimSuccess(claimInsurance.getUser()));
                        logger.info("Insurance claim successful");
                    } else {
                        SystemError systemError = new SystemError(500, "Could not claim insurance");
                        myAgent.getContentManager().fillContent(responseMessage, systemError);
                        logger.error("Could not subscribe package");
                    }

                    myAgent.send(responseMessage);
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while subscribing package. Sending error response...");
            e.printStackTrace();
        }
    }

    @Override
    public boolean done() {
        return false;
    }

}
