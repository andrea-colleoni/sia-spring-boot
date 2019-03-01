package sia.spring.sia.jwt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	@Id
	private String ruolo;
	
	@ManyToMany
	private List<User> utenti;

	@Override
	public String getAuthority() {
		return this.ruolo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public List<User> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<User> utente) {
		this.utenti = utente;
	}

}
