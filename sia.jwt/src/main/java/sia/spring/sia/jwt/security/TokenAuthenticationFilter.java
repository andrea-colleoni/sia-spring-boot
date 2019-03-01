package sia.spring.sia.jwt.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import sia.spring.sia.jwt.model.User;
import sia.spring.sia.jwt.service.UserService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtu;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = Optional
				.ofNullable(request.getHeader("Authorization"))
				.map(v -> v.replace("Bearer", "").trim())
				.orElseThrow(() -> new BadCredentialsException("token mancante"));
		
		User u = jwtu.getUserDatils(token);

		if (u != null) {
			// validare il token
			if (jwtu.validateToken(token, u)) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null, u.getRuoli());
				
				auth.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	
	}

}
