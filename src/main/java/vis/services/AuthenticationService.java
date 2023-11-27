package vis.services;

import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;

public interface AuthenticationService {

	TokenResponse login(LoginRequest loginRequest);

	TokenResponse signup(SignupRequest signupDto);

}
