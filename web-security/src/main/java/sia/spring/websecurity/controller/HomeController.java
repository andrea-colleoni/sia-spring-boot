package sia.spring.websecurity.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sia.spring.websecurity.service.Servizio;

@Controller
@RequestMapping("/private")
public class HomeController {
	
	@Autowired
	private Servizio servizio;

	@RequestMapping("/hello")
	public String hello(Principal principal) {
		System.out.println(principal.getName());
		return "hello";
	}
	
	@RequestMapping("/servizio")
	public String servizio() {
		String str = servizio.dammiStringa();
		System.out.println(str);
		return "hello";
	}
	
	// i ruoli hanno prefisso ROLE_ quando devono essere applicati
	//@Secured({"ROLE_ADMIN"})
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	//@Secured({"ROLE_USER"})
	@RequestMapping("/user")
	public String user() {
		return "user";
	}
	
	//@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/user_admin")
	public String user_admin() {
		return "user_admin";
	}
}
