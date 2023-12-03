package vis.services;

import jade.lang.acl.UnreadableException;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;

public interface AuthenticationService {

	TokenResponse login(LoginRequest loginRequest);

	SignupStatusSchema signup(SignupRequest signupDto) throws IOException, UnreadableException;

}
