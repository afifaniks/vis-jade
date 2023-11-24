package vis.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vis.dto.LoginDto;
import vis.dto.SignupDto;
import vis.dto.TokenDto;

import java.util.Date;

public class JwtAuthenticationService implements AuthenticationService {

    private static final String SECRET_KEY = "yourSecretKey";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 900_000; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1_209_600_000; // 14 days

    @Override
    public TokenDto login(LoginDto loginDto) {
        // Validate the username and password (implement your own validation logic)

        // Assuming validation is successful, generate tokens
        String accessToken = generateToken(loginDto.getUsername(), ACCESS_TOKEN_EXPIRATION_TIME);
        String refreshToken = generateToken(loginDto.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME);

        return new TokenDto(accessToken, refreshToken);
    }

    @Override
    public TokenDto signup(SignupDto signupDto) {
        // Save user details to the database (implement your own logic)

        // Assuming signup is successful, generate tokens
        String accessToken = generateToken(signupDto.getEmail(), ACCESS_TOKEN_EXPIRATION_TIME);
        String refreshToken = generateToken(signupDto.getEmail(), REFRESH_TOKEN_EXPIRATION_TIME);

        return new TokenDto(accessToken, refreshToken);
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
