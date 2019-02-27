package sia.boot.example.corsionline.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Edizione {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Corso corso;
	
	@Temporal(TemporalType.DATE)
	private Date dataInizio;
	@Temporal(TemporalType.DATE)
	private Date dataFine;
	
	@ManyToMany(mappedBy="iscrizioni")
	private List<Persona> iscritti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public List<Persona> getIscritti() {
		return iscritti;
	}

	public void setIscritti(List<Persona> iscritti) {
		this.iscritti = iscritti;
	}

	@Override
	public String toString() {
		return "Edizione [id=" + id + ", corso=" + corso + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", iscritti=" + iscritti + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Edizione other = (Edizione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
