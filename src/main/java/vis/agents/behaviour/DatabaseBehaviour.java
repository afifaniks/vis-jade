package vis.agents.behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.constants.DBOperation;
import vis.entity.DBEntity;
import vis.services.schema.DBTransactionStatusSchema;

import java.io.IOException;

public class DatabaseBehaviour extends CyclicBehaviour {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
																										// settings
																										// from
																										// hibernate.cfg.xml
		.build();

	private SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	public DatabaseBehaviour(Agent agent) {
		super(agent);
	}

	@Override
	public void action() {
		ACLMessage receivedMessage = myAgent.blockingReceive();
		try {

			DBOperation operation = (DBOperation) receivedMessage.getContentObject();

			if (operation.getOperationType() == DBOperation.OperationType.WRITE) {
				writeEntity(operation);
			}

			respondSender(receivedMessage);

		}
		catch (UnreadableException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void respondSender(ACLMessage receivedMessage) throws IOException {
		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(receivedMessage.getSender());
		responseMessage.setContentObject(new DBTransactionStatusSchema(200, "Successful"));
		myAgent.send(responseMessage);
	}

	private void writeEntity(DBOperation operation) {
		DBEntity entity = operation.getEntity();
		logger.info("Writing entity: " + entity);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();
	}

}
