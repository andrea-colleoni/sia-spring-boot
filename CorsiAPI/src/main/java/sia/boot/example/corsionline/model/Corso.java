package sia.boot.example.corsionline.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Corso {
	
	@Id
	private String codiceCorso;
	
	private String titolo;
	
	private String descrizione;
	
	@OneToMany(mappedBy="corso")
	private List<Edizione> edizioni;

	public String getCodiceCorso() {
		return codiceCorso;
	}

	public void setCodiceCorso(String codiceCorso) {
		this.codiceCorso = codiceCorso;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Edizione> getEdizioni() {
		return edizioni;
	}

	public void setEdizioni(List<Edizione> edizioni) {
		this.edizioni = edizioni;
	}

	@Override
	public String toString() {
		return "Corso [codiceCorso=" + codiceCorso + ", titolo=" + titolo + ", descrizione=" + descrizione
				+ ", edizioni=" + edizioni + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceCorso == null) ? 0 : codiceCorso.hashCode());
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
		Corso other = (Corso) obj;
		if (codiceCorso == null) {
			if (other.codiceCorso != null)
				return false;
		} else if (!codiceCorso.equals(other.codiceCorso))
			return false;
		return true;
	}

}
