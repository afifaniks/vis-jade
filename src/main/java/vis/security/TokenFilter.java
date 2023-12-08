package vis.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;
import vis.constants.APIRoutes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/***
 * This filter is used to validate the token
 */
@Component
public class TokenFilter implements Filter {

	/***
	 * This list contains the protected routes
	 */
	private List<String> protectedRoutes = Arrays.asList(APIRoutes.SUBSCRIBE, APIRoutes.REGISTER_VEHICLE,
			APIRoutes.GET_PACKAGES, APIRoutes.CLAIM, APIRoutes.PROFILE, APIRoutes.GET_USER);

	private boolean validateToken(String token) {
		String SECRET_KEY = "yourSecr456hfdghjghuj45h4566rghfghhfghryt45etKey";
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			// Token is invalid
			return false;
		}
	}

	/***
	 * This method is used to validate the token
	 * @param servletRequest The request object
	 * @param servletResponse The response object
	 * @param filterChain The filter chain object
	 * @throws IOException if an I/O error occurs
	 * @throws ServletException if a servlet error occurs
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		final String requestURI = ((RequestFacade) servletRequest).getRequestURI();

		if (protectedRoutes.contains(requestURI)) {
			String auth = req.getHeader("Authorization");
			String token = auth.replace("Bearer ", "");

			if (!validateToken(token)) {
				throw new ServletException("Invalid token");
			}

		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

}