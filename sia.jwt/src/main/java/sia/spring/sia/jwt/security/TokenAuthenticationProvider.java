package sia.spring.sia.jwt.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import sia.spring.sia.jwt.service.UserService;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private UserService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		Object token = authentication.getCredentials();
		return Optional
				.ofNullable(token)
				.flatMap(t ->
						Optional
							.of(userService.authenticateByToken(String.valueOf(t))))
				.orElseThrow(() -> new BadCredentialsException("credenziali non valide"));
	}
}
