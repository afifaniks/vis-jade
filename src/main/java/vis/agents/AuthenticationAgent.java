package vis.agents;

import com.google.gson.Gson;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.dto.LoginDto;
import vis.dto.SignupDto;
import vis.services.AuthenticationService;
import vis.services.AuthenticationServiceImpl;

public class AuthenticationAgent extends Agent {

	private final AuthenticationService authenticationService = new AuthenticationServiceImpl();

	private final Gson gson = new Gson();

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	@Override
	protected void setup() {
		logger.debug("Authentication agent started. AID: " + getAID().getName());
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				ACLMessage receivedMessage = null;
				receivedMessage = receive();

				if (receivedMessage == null) {
					block();
					return;
				}

				try {
					AgentAction action = (AgentAction) receivedMessage.getContentObject();
					String response = "";

					if (action.getAction().equals("login")) {
						response = authenticationService.login(gson.fromJson(action.getContents(), LoginDto.class));
					}
					else if (action.getAction().equals("signup")) {
						response = authenticationService.signup(gson.fromJson(action.getContents(), SignupDto.class));
					}

					ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
					responseMessage.addReceiver(receivedMessage.getSender());
					responseMessage.setContent(response);
					myAgent.send(responseMessage);

				}
				catch (UnreadableException e) {
					throw new RuntimeException(e);
				}

				logger.debug("Request received from admin agent");

			}
		});
	}

}
