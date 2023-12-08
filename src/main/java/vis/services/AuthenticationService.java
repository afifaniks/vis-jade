package vis.services;

import jade.lang.acl.UnreadableException;
import vis.dto.request.LoginRequest;
import vis.dto.response.TokenResponse;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.SignupStatusSchema;

import java.io.IOException;

/***
 * This Authentication service class is used to authenticate users using JWT tokens.
 */
public interface AuthenticationService {

    TokenResponse login(LoginRequest loginRequest) throws IOException, UnreadableException;

    SignupStatusSchema signup(SignupRequestSchema signupDto) throws IOException, UnreadableException;

}
