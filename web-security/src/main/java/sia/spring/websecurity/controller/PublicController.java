package sia.spring.websecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sia.spring.websecurity.service.Servizio;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private Servizio servizio;

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/servizio")
	public String servizio() {
		String str = servizio.dammiStringa();
		System.out.println(str);
		return "hello";
	}
	
	// i ruoli hanno prefisso ROLE_ quando devono essere applicati
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
