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

/***
 * This class implements the CustomerAssistantService interface.
 */
public class CustomerAssistantServiceImpl implements CustomerAssistantService {

	private Agent agent;

	public CustomerAssistantServiceImpl(Agent agent) {
		this.agent = agent;
	}

	/***
	 * This method is used to get the services done which are directly related to the
	 * customer.
	 * @param recommendationRequestSchema This is the request schema which contains all
	 * the information needed to get the services done which are directly related
	 * @return This returns the services done which are directly related to the customer.
	 * @throws UnreadableException This exception is thrown when the ACLMessage received
	 * from the agent is not of the correct type.
	 * @throws IOException This exception is thrown when the ACLMessage received from the
	 * agent is not of the correct type.
	 */
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

	/***
	 * This method is used to subscribe the services done which are directly related to
	 * the customer.
	 * @param subscriptionRequestSchema the subscription request schema
	 * @return The subscription
	 * @throws IOException This exception is thrown when the ACLMessage received from the
	 * agent is not of the correct type.
	 * @throws UnreadableException This exception is thrown when the ACLMessage received
	 * from the agent is not of the correct type.
	 */
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

	/***
	 * This method is used to claim the services done which are directly related to the
	 * customer.
	 * @param vehicleRegistrationSchema the vehicle registration schema
	 * @return The vehicle registration
	 * @throws IOException This exception is thrown when the ACLMessage received from the
	 * agent is not of the correct type.
	 * @throws UnreadableException This exception is thrown when the ACLMessage received
	 * from the agent is not of the correct type.
	 */
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

	/***
	 * This method is used to get the user profile of the customer.
	 * @param getUserRequestSchema This is the request schema which contains all the
	 * information needed to get the user profile of the customer.
	 * @return This returns the user profile of the customer.
	 * @throws UnreadableException This exception is thrown when the ACLMessage received
	 * from the agent is not of the correct type.
	 * @throws IOException This exception is thrown when the ACLMessage received from the
	 * agent is not of the correct type.
	 */
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
