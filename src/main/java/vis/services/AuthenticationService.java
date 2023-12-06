package vis.services;

import jade.lang.acl.UnreadableException;
import vis.dto.request.LoginRequest;
import vis.dto.response.TokenResponse;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;

public interface AuthenticationService {

	TokenResponse login(LoginRequest loginRequest) throws IOException, UnreadableException;

	SignupStatusSchema signup(SignupRequestSchema signupDto) throws IOException, UnreadableException;

}
