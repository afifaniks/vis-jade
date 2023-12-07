package vis.services;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import vis.constants.AgentIdentifier;
import vis.constants.DBOperation;
import vis.services.schema.*;

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
	public SubscriptionStatusSchema subscribePackage(SubscriptionRequestSchema subscriptionRequestSchema)
			throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.SUBSCRIBE, subscriptionRequestSchema);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		DBTransactionStatusSchema statusSchema = (DBTransactionStatusSchema) responseMessage.getContentObject();

		if (statusSchema.getStatus() == 200) {
			return new SubscriptionStatusSchema(200, "Subscription successful");
		}
		return new SubscriptionStatusSchema(500, "Subscription unsuccessful");
	}

	@Override
	public VehicleRegistrationStatusSchema registerVehicle(VehicleRegistrationSchema vehicleRegistrationSchema)
			throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.REGISTER_VEHICLE, vehicleRegistrationSchema);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		DBTransactionStatusSchema statusSchema = (DBTransactionStatusSchema) responseMessage.getContentObject();

		if (statusSchema.getStatus() == 200) {
			return new VehicleRegistrationStatusSchema(200, "Vehicle Registration successful");
		}
		return new VehicleRegistrationStatusSchema(500, "Vehicle Registration unsuccessful");
	}

	@Override
	public UserProfileSchema getUser(GetUserRequestSchema getUserRequestSchema)
			throws UnreadableException, IOException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.GET_USER, getUserRequestSchema);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		return (UserProfileSchema) responseMessage.getContentObject();
	}

}
