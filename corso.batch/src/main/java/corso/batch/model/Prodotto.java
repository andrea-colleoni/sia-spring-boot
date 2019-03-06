package corso.batch.model;

import java.math.BigDecimal;

public class Prodotto {
	
	private String id;
	private String nome;
	private String decsrizione;
	private BigDecimal prezzo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDecsrizione() {
		return decsrizione;
	}
	public void setDecsrizione(String decsrizione) {
		this.decsrizione = decsrizione;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	
	

}
