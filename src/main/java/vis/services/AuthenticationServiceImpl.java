package vis.services;

import vis.dto.LoginDto;
import vis.dto.SignupDto;

public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public String login(LoginDto loginDto) {
		return "Login called";
	}

	@Override
	public String signup(SignupDto signupDto) {
		return "Signup called";
	}

}
