package sia.bootdatajpa.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	private String cognome;
	
	private Integer numeroScarpe;
	private Integer altezzaInCm;
	private Float pesoInKg;

	private Date dataNascita;
	
	@Lob
	private byte[] immagineProfilo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getNumeroScarpe() {
		return numeroScarpe;
	}

	public void setNumeroScarpe(Integer numeroScarpe) {
		this.numeroScarpe = numeroScarpe;
	}

	public Integer getAltezzaInCm() {
		return altezzaInCm;
	}

	public void setAltezzaInCm(Integer altezzaInCm) {
		this.altezzaInCm = altezzaInCm;
	}

	public Float getPesoInKg() {
		return pesoInKg;
	}

	public void setPesoInKg(Float pesoInKg) {
		this.pesoInKg = pesoInKg;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public byte[] getImmagineProfilo() {
		return immagineProfilo;
	}

	public void setImmagineProfilo(byte[] immagineProfilo) {
		this.immagineProfilo = immagineProfilo;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", numeroScarpe=" + numeroScarpe
				+ ", altezzaInCm=" + altezzaInCm + ", pesoInKg=" + pesoInKg + ", dataNascita=" + dataNascita
				+ ", immagineProfilo=" + Arrays.toString(immagineProfilo) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altezzaInCm == null) ? 0 : altezzaInCm.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(immagineProfilo);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroScarpe == null) ? 0 : numeroScarpe.hashCode());
		result = prime * result + ((pesoInKg == null) ? 0 : pesoInKg.hashCode());
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
		if (altezzaInCm == null) {
			if (other.altezzaInCm != null)
				return false;
		} else if (!altezzaInCm.equals(other.altezzaInCm))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(immagineProfilo, other.immagineProfilo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroScarpe == null) {
			if (other.numeroScarpe != null)
				return false;
		} else if (!numeroScarpe.equals(other.numeroScarpe))
			return false;
		if (pesoInKg == null) {
			if (other.pesoInKg != null)
				return false;
		} else if (!pesoInKg.equals(other.pesoInKg))
			return false;
		return true;
	}
	
	

}
