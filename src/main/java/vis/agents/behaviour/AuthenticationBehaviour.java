package vis.agents.behaviour;

import com.google.gson.Gson;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AgentActionIdentifier;
import vis.agents.AuthenticationAgent;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;
import vis.ontology.VISOntology;
import vis.ontology.predicates.LoginSuccess;
import vis.ontology.predicates.SignupSuccess;
import vis.services.AuthenticationService;
import vis.services.JwtAuthenticationService;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;
import java.util.Objects;

public class AuthenticationBehaviour extends CyclicBehaviour {

	private final AuthenticationService authenticationService;

	private final Codec codec = new SLCodec();

	private final Ontology ontology = VISOntology.getInstance();

	MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
			MessageTemplate.MatchOntology(ontology.getName()));

	private final Gson gson = new Gson();

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	public AuthenticationBehaviour(Agent agent) {
		authenticationService = new JwtAuthenticationService(agent);
	}

	@Override
	public void action() {
		myAgent.getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL);
		myAgent.getContentManager().registerOntology(ontology);
		ACLMessage receivedMessage = null;
		receivedMessage = myAgent.blockingReceive();

		try {
			AgentActionIdentifier action = (AgentActionIdentifier) receivedMessage.getContentObject();
			ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
			responseMessage.setLanguage(codec.getName());
			responseMessage.setOntology(ontology.getName());

			if (action.getAction().equals("login")) {
				TokenResponse response = authenticationService
					.login(gson.fromJson(action.getContents(), LoginRequest.class));
				responseMessage.setContentObject(response);
				myAgent.getContentManager()
					.fillContent(responseMessage,
							new LoginSuccess(response.getAccessToken(), response.getRefreshToken()));
			}
			else if (action.getAction().equals("signup")) {
				SignupRequest signupRequest = gson.fromJson(action.getContents(), SignupRequest.class);
				SignupStatusSchema response = authenticationService.signup(signupRequest);
				if (response.getStatus() == 200) {
					myAgent.getContentManager()
						.fillContent(responseMessage, new SignupSuccess(response.getStatus(), response.getMessage()));
				}
			}

			responseMessage.addReceiver(receivedMessage.getSender());
			myAgent.send(responseMessage);

		}
		catch (UnreadableException | IOException e) {
			logger.error(String.valueOf(e));
			throw new RuntimeException(e);
		}
		catch (OntologyException | Codec.CodecException e) {
			throw new RuntimeException(e);
		}

		logger.debug("Request received from admin agent");
	}

}
