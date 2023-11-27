package vis.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vis.dto.request.LoginRequest;
import vis.dto.request.SignupRequest;
import vis.dto.response.TokenResponse;

import java.util.Date;

public class JwtAuthenticationService implements AuthenticationService {

	private static final String SECRET_KEY = "yourSecretKey";

	private static final long ACCESS_TOKEN_EXPIRATION_TIME = 900_000; // 15 minutes

	private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

	@Override
	public TokenResponse login(LoginRequest loginRequest) {
		// Validate the username and password (implement your own validation logic)

		// Assuming validation is successful, generate tokens
		String accessToken = generateToken(loginRequest.getUsername(), ACCESS_TOKEN_EXPIRATION_TIME);
		String refreshToken = generateToken(loginRequest.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME);

		return new TokenResponse(accessToken, refreshToken);
	}

	@Override
	public TokenResponse signup(SignupRequest signupDto) {
		// Save user details to the database (implement your own logic)

		// Assuming signup is successful, generate tokens
		String accessToken = generateToken(signupDto.getEmail(), ACCESS_TOKEN_EXPIRATION_TIME);
		String refreshToken = generateToken(signupDto.getEmail(), REFRESH_TOKEN_EXPIRATION_TIME);

		return new TokenResponse(accessToken, refreshToken);
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
