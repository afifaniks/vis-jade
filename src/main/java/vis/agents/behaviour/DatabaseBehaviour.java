package vis.agents.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.constants.DBOperation;
import vis.dto.request.LoginRequest;
import vis.services.DatabaseService;
import vis.services.DatabaseServiceImpl;
import vis.services.schema.DBTransactionStatusSchema;
import vis.services.schema.SignupRequestSchema;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class DatabaseBehaviour extends CyclicBehaviour {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);
	private final DatabaseService databaseService = new DatabaseServiceImpl();


	public DatabaseBehaviour(Agent agent) {
		super(agent);
	}

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

		}
		catch (UnreadableException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List readEntity(DBOperation operation) {
//		Session session = sessionFactory.openSession();
//		String hql = "FROM " + operation.getTableName() + " WHERE " + operation.getQuery();
//		Query query = session.createQuery(hql);
//
//		return query.list();
		return null;
	}

	private void respondSender(AID sender, Serializable object) throws IOException {
		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(sender);
		responseMessage.setContentObject(object);
		myAgent.send(responseMessage);
	}

	private void writeEntity(DBOperation operation) {
//		DBEntity entity = operation.getEntity();
//		logger.info("Writing entity: " + entity);
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.persist(entity);
//		session.getTransaction().commit();
//		session.close();
	}

}
