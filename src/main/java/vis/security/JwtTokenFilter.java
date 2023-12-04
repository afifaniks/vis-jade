package vis.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import vis.services.JwtAuthenticationService;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtAuthenticationService jwtAuthenticationService;

    public JwtTokenFilter(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtAuthenticationService.validateToken(token)) {
                String username = jwtAuthenticationService.extractUsername(token);
                List<SimpleGrantedAuthority> authorities = Collections.emptyList(); // If no roles, use empty list

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}

