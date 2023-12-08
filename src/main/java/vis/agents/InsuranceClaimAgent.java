package vis.agents;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.behaviour.InsuranceClaimBehaviour;

/***
 * This agent is responsible for claiming insurance on demand.
 */
public class InsuranceClaimAgent extends Agent {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	@Override
	protected void setup() {
		logger.info("InsuranceClaim agent started. AID: " + getAID().getName());
		addBehaviour(new InsuranceClaimBehaviour(this));
	}

}
