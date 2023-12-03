package vis.agents.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.constants.DBOperation;
import vis.entity.DBEntity;
import vis.services.schema.DBTransactionStatusSchema;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseBehaviour extends CyclicBehaviour {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

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
				respondSender(receivedMessage.getSender(), new DBTransactionStatusSchema(200, "Successful"));
			}

			if (operation.getOperationType() == DBOperation.OperationType.READ) {
				List results = readEntity(operation);
				respondSender(receivedMessage.getSender(), (Serializable) results);
			}

		}
		catch (UnreadableException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List readEntity(DBOperation operation) {
		Session session = sessionFactory.openSession();
		String hql = "FROM " + operation.getTableName() + " WHERE " + operation.getQuery();
		Query query = session.createQuery(hql);

		return query.list();
	}

	private void respondSender(AID sender, Serializable object) throws IOException {
		ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
		responseMessage.addReceiver(sender);
		responseMessage.setContentObject(object);
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
