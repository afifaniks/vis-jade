package vis.agents;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.behaviour.CustomerAssistantBehaviour;

public class CustomerAssistantAgent extends Agent {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	@Override
	protected void setup() {
		logger.info("CustomerAssistant agent started. AID: " + getAID().getName());
		addBehaviour(new CustomerAssistantBehaviour(this));
	}

}
