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
import vis.dto.request.LoginRequest;
import vis.dto.response.TokenResponse;
import vis.services.schema.DBTransactionStatusSchema;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;
import java.util.Date;

/***
 * This service class implements the AuthenticationService interface.
 */
public class JwtAuthenticationService implements AuthenticationService {

    private Agent agent;

    private Gson gson = new Gson();

    private static final String SECRET_KEY = "yourSecr456hfdghjghuj45h4566rghfghhfghryt45etKey";

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

    public JwtAuthenticationService(Agent agent) {
        this.agent = agent;
    }

    /***
     * This method generates a JWT token and authenticates an existing user.
     * @param loginRequest contains the username and password of the user to be authenticated.
     * @return a TokenResponse object containing the JWT token and the user's username.
     * @throws IOException throws IOException
     * @throws UnreadableException throws UnreadableException
     */
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

    /***
     * This method creates a new user.
     * @param signupDto contains the username and password of the user to be created.
     * @return a SignupStatusSchema object containing the status code and message.
     * @throws IOException throws IOException
     */
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

        return new SignupStatusSchema(statusSchema.getStatus(), statusSchema.getMessage());
    }


    private String generateToken(String subject, long expirationTime) {
        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

}
