package sia.spring.websecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class Servizio {
	
	@Secured({"ROLE_USER"})
	public String dammiStringa() {
		return "valore della stringa";
	}

}
