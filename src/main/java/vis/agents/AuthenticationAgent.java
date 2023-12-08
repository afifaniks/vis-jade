package vis.agents;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.behaviour.AuthenticationBehaviour;

/***
 * This agent is responsible for authentication process of the system.
 */
public class AuthenticationAgent extends Agent {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

    @Override
    protected void setup() {
        logger.debug("Authentication agent started. AID: " + getAID().getName());
        addBehaviour(new AuthenticationBehaviour(this));
    }

}
