package vis.services;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import vis.constants.AgentIdentifier;
import vis.constants.DBOperation;
import vis.services.schema.ClaimRequestSchema;
import vis.services.schema.DBTransactionStatusSchema;
import vis.services.schema.InsuranceClaimStatusSchema;

import java.io.IOException;

/***
 * This class implements the InsuranceClaimService interface.
 */
public class InsuranceClaimServiceImpl implements InsuranceClaimService {

	private Agent agent;

	public InsuranceClaimServiceImpl(Agent agent) {
		this.agent = agent;
	}

	/***
	 * This method implements the InsuranceClaimService interface.
	 * @param claimRequest is a ClaimRequestSchema object.
	 * @return is an InsuranceClaimStatusSchema object.
	 * @throws IOException throws Input or OutputException
	 * @throws UnreadableException throws UnreadableException
	 */
	@Override
	public InsuranceClaimStatusSchema claimInsurance(ClaimRequestSchema claimRequest)
			throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.CLAIM, claimRequest);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		DBTransactionStatusSchema statusSchema = (DBTransactionStatusSchema) responseMessage.getContentObject();

		if (statusSchema.getStatus() == 200) {
			return new InsuranceClaimStatusSchema(200, "Subscription successful");
		}

		return new InsuranceClaimStatusSchema(statusSchema.getStatus(), statusSchema.getMessage());
	}

}
