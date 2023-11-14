package vis.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdminAgent extends Agent {
    private final Logger logger = LoggerFactory.getLogger(AdminAgent.class);
    @Override
    protected void setup() {
        logger.debug("Admin agent started. AID: " + getAID().getName());
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = null;
                receivedMessage = blockingReceive();
                String request = receivedMessage.getContent();
                logger.debug("Request received from gateway handler: " + request);

                String response = doSomething(request);

                ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
                responseMessage.addReceiver(receivedMessage.getSender());
                responseMessage.setContent(response);
                myAgent.send(responseMessage);
            }
        });
    }

    private String doSomething (String request) {
        return request + request;
    }
}
