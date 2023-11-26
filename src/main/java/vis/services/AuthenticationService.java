package vis.services;

import vis.dto.LoginDto;
import vis.dto.SignupDto;
import vis.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public interface AuthenticationService {
	TokenDto login(LoginDto loginDto);
	TokenDto signup(SignupDto signupDto);
}


