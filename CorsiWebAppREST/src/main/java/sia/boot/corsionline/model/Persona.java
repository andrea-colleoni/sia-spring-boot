package sia.boot.corsionline.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Persona {
	
	@Id
	@GeneratedValue
	private Integer matricola;
	
	private String nome;
	private String cognome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
		
	@ManyToMany
	private List<Edizione> iscrizioni;

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public List<Edizione> getIscrizioni() {
		return iscrizioni;
	}

	public void setIscrizioni(List<Edizione> iscrizioni) {
		this.iscrizioni = iscrizioni;
	}

	@Override
	public String toString() {
		return "Persona [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", iscrizioni=" + iscrizioni + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}

}
