package vis.agents.behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import vis.services.schema.VehicleRegistrationSchema;

/***
 * This behavior represents how customer provided information can be verified through a
 * separate agent.
 */
public class VerificationBehavior extends CyclicBehaviour {

	public VerificationBehavior(Agent a) {
		super(a);
	}

	@Override
	public void action() {
		ACLMessage receivedMessage = myAgent.blockingReceive();
		try {
			VehicleRegistrationSchema schema = (VehicleRegistrationSchema) receivedMessage.getContentObject();
			ACLMessage responseMessage;

			if (verifyInformation(schema)) {
				responseMessage = new ACLMessage(ACLMessage.CONFIRM);
			}
			else {
				responseMessage = new ACLMessage(ACLMessage.DISCONFIRM);
			}
			responseMessage.addReceiver(receivedMessage.getSender());
			myAgent.send(responseMessage);

		}
		catch (UnreadableException e) {
			throw new RuntimeException(e);
		}
	}

	/***
	 * Using this method we can verify the customer information by external web services.
	 * This is used as a demonstration purpose for future implementation.
	 * @param schema Schema to be verified
	 * @return true when verified; false otherwise
	 */
	private boolean verifyInformation(VehicleRegistrationSchema schema) {
		return true;
	}

}
