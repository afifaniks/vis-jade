package vis.agents;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.behaviour.VerificationBehavior;

/***
 * This agent is responsible for verifying customer vehicle information when applying for
 * insurance package.
 */
public class VerificationAgent extends Agent {

	private final Logger logger = LoggerFactory.getLogger(VerificationAgent.class);

	@Override
	protected void setup() {
		logger.info("CustomerVerification agent started. AID: " + getAID().getName());
		addBehaviour(new VerificationBehavior(this));
	}

}