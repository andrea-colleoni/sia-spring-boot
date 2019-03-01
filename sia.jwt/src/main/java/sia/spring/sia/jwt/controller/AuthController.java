package sia.spring.sia.jwt.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sia.spring.sia.jwt.security.JwtAuthRequest;
import sia.spring.sia.jwt.security.JwtTokenUtil;
import sia.spring.sia.jwt.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtu;

	@RequestMapping(value="public/login", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest authReq, HttpServletResponse response) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authReq.getUsername(), authReq.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		UserDetails ud = userService.loadUserByUsername(authReq.getUsername());
		
		String token = jwtu.generateToken(ud);
		response.setHeader("X-Auth", token);
		
		return ResponseEntity.ok(ud);
		/*Optional
		.ofNullable(token)
		.flatMap(t ->
				Optional
					.of(userService.authenticateByToken(String.valueOf(t))))
					.orElseThrow(() -> new BadCredentialsException("credenziali non valide"));
		*/
	}
}
