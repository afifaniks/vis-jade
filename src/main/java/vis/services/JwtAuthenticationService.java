package vis.services;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import vis.constants.AgentIdentifier;
import vis.constants.DBOperation;
import vis.constants.DBTableNames;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;
import vis.entity.UserEntity;
import vis.services.schema.DBTransactionStatusSchema;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationService implements AuthenticationService {

	private Agent agent;

	private Gson gson = new Gson();

	private static final String SECRET_KEY = "yourSecr456hfdh4566r45etKey";

	private static final long ACCESS_TOKEN_EXPIRATION_TIME = 900_000; // 15 minutes

	private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

	public JwtAuthenticationService(Agent agent) {
		this.agent = agent;
	}

	@Override
	public TokenResponse login(LoginRequest loginRequest) {
		// Validate the username and password (implement your own validation logic)

		// Assuming validation is successful, generate tokens
		String accessToken = generateToken(loginRequest.getUsername(), ACCESS_TOKEN_EXPIRATION_TIME);
		String refreshToken = generateToken(loginRequest.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME);

		return new TokenResponse(accessToken, refreshToken);
	}

	@Override
	public SignupStatusSchema signup(SignupRequest signupDto) throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBTableNames.USER, DBOperation.OperationType.WRITE,
				gson.fromJson(gson.toJson(signupDto), UserEntity.class));

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		DBTransactionStatusSchema statusSchema = (DBTransactionStatusSchema) responseMessage.getContentObject();

		if (statusSchema.getStatus() == 200) {
			return new SignupStatusSchema(200, "Signup successful");
		}

		return new SignupStatusSchema(500, "Signup failed");
	}

	private String generateToken(String subject, long expirationTime) {
		return Jwts.builder()
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();
	}

}
