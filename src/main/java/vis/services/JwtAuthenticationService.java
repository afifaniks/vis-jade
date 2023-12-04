package vis.services;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import org.springframework.stereotype.Service;
import vis.constants.AgentIdentifier;
import vis.constants.DBOperation;
import vis.dto.request.LoginRequest;
import vis.dto.response.TokenResponse;
import vis.services.schema.DBTransactionStatusSchema;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;
import java.util.Date;

@Service
public class JwtAuthenticationService implements AuthenticationService {

	private Agent agent;

	private Gson gson = new Gson();

	private static final String SECRET_KEY = "yourSecr456hfdghjghuj45h4566rghfghhfghryt45etKey";

	private static final long ACCESS_TOKEN_EXPIRATION_TIME = 900_000; // 15 minutes

	private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

	public JwtAuthenticationService(Agent agent) {
		this.agent = agent;
	}

	@Override
	public TokenResponse login(LoginRequest loginRequest) throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.LOGIN, loginRequest);

		ACLMessage dbRequestMessage = new ACLMessage(ACLMessage.REQUEST);
		dbRequestMessage.addReceiver(new AID(AgentIdentifier.DATABASE, AID.ISLOCALNAME));
		dbRequestMessage.setContentObject(dbOperation);

		this.agent.send(dbRequestMessage);

		ACLMessage responseMessage = this.agent.blockingReceive();
		DBTransactionStatusSchema statusSchema = (DBTransactionStatusSchema) responseMessage.getContentObject();

		String accessToken = null;
		String refreshToken = null;

		if (statusSchema.getStatus() == 200) {
			accessToken = generateToken(loginRequest.getUsername(), ACCESS_TOKEN_EXPIRATION_TIME);
			refreshToken = generateToken(loginRequest.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME);
		}

		return new TokenResponse(accessToken, refreshToken);
	}

	@Override
	public SignupStatusSchema signup(SignupRequestSchema signupDto) throws IOException, UnreadableException {
		DBOperation dbOperation = new DBOperation(DBOperation.Operation.SIGNUP, signupDto);

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

	public String generateToken(String subject, long expirationTime) {
		return Jwts.builder()
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();
	}

//	public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//			return true;
//		} catch (Exception e) {
//			// Token is invalid
//			return false;
//		}
//	}
//
//	public String extractUsername(String token) {
//		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//		return claims.getSubject();
//	}

}
