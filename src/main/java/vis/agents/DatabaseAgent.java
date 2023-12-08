package vis.agents;

import jade.core.Agent;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.behaviour.DatabaseBehaviour;

/***
 * This agent is responsible for handling the database operations of the VIS system.
 */
public class DatabaseAgent extends Agent {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	@Override
	protected void setup() {
		logger.debug("Database agent started. AID: " + getAID().getName());
		addBehaviour(new DatabaseBehaviour(this));
	}

}
