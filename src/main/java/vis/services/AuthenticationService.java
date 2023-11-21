package vis.services;

import vis.dto.LoginDto;
import vis.dto.SignupDto;

public interface AuthenticationService {

	public String login(LoginDto loginDto);

	public String signup(SignupDto signupDto);

}
