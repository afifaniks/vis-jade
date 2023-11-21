package vis.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationAgent extends Agent {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

    @Override
    protected void setup() {
        logger.debug("Authentication agent started. AID: " + getAID().getName());
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = null;
                receivedMessage = receive();

                if (receivedMessage == null) {
                    block();
                    return;
                }

                String request = receivedMessage.getContent();

                System.out.println("Request received from agent: " + request);

//                String response = doSomething(request);
//
//                ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
//                responseMessage.addReceiver(receivedMessage.getSender());
//                responseMessage.setContent(response);
//                myAgent.send(responseMessage);
            }
        });
    }
}
