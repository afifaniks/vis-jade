package vis.agents.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.constants.DBOperation;
import vis.dto.request.LoginRequest;
import vis.services.DatabaseService;
import vis.services.DatabaseServiceImpl;
import vis.services.schema.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/***
 * This behavioural class is associated to the DatabaseAgent and all the database
 * operations are controlled by this behavior. Each of the existing agents communicates
 * with this agent when there is a necessity of a database operation based on the customer
 * request. This is the single point of entry to access the VIS database.
 */
public class DatabaseBehaviour extends CyclicBehaviour {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

    private final DatabaseService databaseService = new DatabaseServiceImpl();

    public DatabaseBehaviour(Agent agent) {
        super(agent);
    }

    /***
     * Preceded by the DBOperation class, this action implementation invokes service layer
     * methods to perform required database requests by the other agents. The method
     * accepts an instance of DBOperation through ACL message indicating the database
     * operation that is need to be done. Depending on the operation outcome, it also
     * sends back the transaction status, query results, etc. to the calling agent.
     */
    @Override
    public void action() {
        ACLMessage receivedMessage = myAgent.blockingReceive();
        try {

            DBOperation operation = (DBOperation) receivedMessage.getContentObject();

            if (operation.getOperation() == DBOperation.Operation.LOGIN) {
                LoginRequest loginRequest = (LoginRequest) operation.getAdditionalObject();
                boolean loginSuccess = this.databaseService.login(loginRequest.getUsername(), loginRequest.getPassword());

                if (loginSuccess) {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
                } else {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(500, "Failed"));
                }
            }

            if (operation.getOperation() == DBOperation.Operation.SIGNUP) {
                SignupRequestSchema signupRequest = (SignupRequestSchema) operation.getAdditionalObject();
                boolean signupSuccess = this.databaseService.signup(signupRequest);

                if (signupSuccess) {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
                } else {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(500, "Failed"));
                }
            }

            if (operation.getOperation() == DBOperation.Operation.REGISTER_VEHICLE) {
                VehicleRegistrationSchema vehicleRegistrationSchema = (VehicleRegistrationSchema) operation.getAdditionalObject();
                boolean vehicleRegistrationSuccess = this.databaseService.vehicleRegistration(vehicleRegistrationSchema);

                if (vehicleRegistrationSuccess) {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
                } else {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(500, "Failed"));
                }
            }

            if (operation.getOperation() == DBOperation.Operation.GET_PACKAGES) {
                RecommendationRequestSchema recommendationRequestSchema = (RecommendationRequestSchema) operation.getAdditionalObject();
                ArrayList<InsurancePackageSchema> packages = this.databaseService.getPackages(recommendationRequestSchema.getUserEmail(), recommendationRequestSchema.getVehicleId());

                respondSender(receivedMessage.getSender(), packages);
            }

            if (operation.getOperation() == DBOperation.Operation.GET_USER) {
                GetUserRequestSchema getUserRequestSchema = (GetUserRequestSchema) operation.getAdditionalObject();
                logger.info("Getting user: " + getUserRequestSchema.getEmail());
                UserProfileSchema user = this.databaseService.getUserRequest(getUserRequestSchema.getEmail());

                respondSender(receivedMessage.getSender(), user);
            }

            if (operation.getOperation() == DBOperation.Operation.SUBSCRIBE) {
                SubscriptionRequestSchema subscriptionRequestSchema = (SubscriptionRequestSchema) operation.getAdditionalObject();
                boolean subscriptionSuccess = this.databaseService.subscribe(subscriptionRequestSchema);

                if (subscriptionSuccess) {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
                } else {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(500, "Failed"));
                }
            }

            if (operation.getOperation() == DBOperation.Operation.CLAIM) {
                ClaimRequestSchema claimRequestSchema = (ClaimRequestSchema) operation.getAdditionalObject();
                boolean claimSuccess = this.databaseService.claimInsurance(claimRequestSchema);

                if (claimSuccess) {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
                } else {
                    respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(500, "Failed"));
                }
            }

        } catch (UnreadableException | IOException e) {
            logger.error(e.toString());
        } catch (ConstraintViolationException e) {
            logger.error(e.toString());
            try {
                respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(403, "Already exists"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /***
     * This method sends the result of db operation to the calling agent through ACL
     * messaging.
     * @param sender The AID of the sender.
     * @param object The response object.
     * @throws IOException When message fails to transmit.
     */
    private void respondSender(AID sender, Object object) throws IOException {
        ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
        responseMessage.addReceiver(sender);
        responseMessage.setContentObject((Serializable) object);
        myAgent.send(responseMessage);
    }

}
