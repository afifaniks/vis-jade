package vis.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
				AgentAction request = null;
				try {
					request = (AgentAction) receivedMessage.getContentObject();
				}
				catch (UnreadableException e) {
					throw new RuntimeException(e);
				}
				logger.debug("Request received from gateway handler: " + request);

				String response = null;
				try {
					response = relayRequest(request);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}

				ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
				responseMessage.addReceiver(receivedMessage.getSender());
				responseMessage.setContent(response);
				myAgent.send(responseMessage);
			}
		});
	}

	private String relayRequest(AgentAction request) throws IOException {
		if (request.getTargetAgent() == AgentType.AUTHENTICATION) {
			ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
			message.setContentObject(request);
			message.addReceiver(new AID(AgentNames.authentication, AID.ISLOCALNAME));
			send(message);

			ACLMessage receivedMessage = blockingReceive();

			return receivedMessage.getContent();
		}

		return null;
	}

}
