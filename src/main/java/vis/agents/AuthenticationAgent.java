package vis.agents;

import com.google.gson.Gson;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;
import vis.services.AuthenticationService;
import vis.services.JwtAuthenticationService;

import java.io.IOException;

public class AuthenticationAgent extends Agent {

	private final AuthenticationService authenticationService = new JwtAuthenticationService();

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
					TokenResponse response = null;

					if (action.getAction().equals("login")) {
						response = authenticationService.login(gson.fromJson(action.getContents(), LoginRequest.class));
					}
					else if (action.getAction().equals("signup")) {
						response = authenticationService
							.signup(gson.fromJson(action.getContents(), SignupRequest.class));
					}

					ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
					responseMessage.addReceiver(receivedMessage.getSender());
					responseMessage.setContentObject(response);
					myAgent.send(responseMessage);

				}
				catch (UnreadableException | IOException e) {
					logger.error(String.valueOf(e));
					throw new RuntimeException(e);
				}

				logger.debug("Request received from admin agent");

			}
		});
	}

}
