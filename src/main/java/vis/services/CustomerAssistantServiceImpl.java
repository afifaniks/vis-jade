package vis.services;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import vis.constants.AgentIdentifier;
import vis.constants.DBOperation;
import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.RecommendationRequestSchema;
import vis.services.schema.SubscriptionSchema;
import vis.services.schema.SubscriptionStatusSchema;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerAssistantServiceImpl implements CustomerAssistantService {

	private Agent agent;

	public CustomerAssistantServiceImpl(Agent agent) {
		this.agent = agent;
	}

	@Override
	public ArrayList<InsurancePackageSchema> getPackageRecommendation(
			RecommendationRequestSchema recommendationRequestSchema) throws UnreadableException, IOException {

		DBOperation dbOperation = new DBOperation(DBOperation.Operation.GET_PACKAGES, recommendationRequestSchema);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();

		return (ArrayList<InsurancePackageSchema>) responseMessage.getContentObject();
	}

	@Override
	public SubscriptionStatusSchema subscribePackage(SubscriptionSchema subscriptionSchema) {
		// TODO: Write to database
		return new SubscriptionStatusSchema(200, "Subscription successful");
	}

}
