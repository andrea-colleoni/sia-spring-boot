package sia.batch.sales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlussoFirmaDigitale {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=5)
	private String codiceIstituto;
	@Column(length=7)
	private String codiceCertificatoFD;
	
	@Column(length=250)
	private String issuer;
	
	@Column(length=128)
	public String serialNumber;
	
	@Column(length=30)
	public String descrizioneAnagrafica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceIstituto() {
		return codiceIstituto;
	}

	public void setCodiceIstituto(String codiceIstituto) {
		this.codiceIstituto = codiceIstituto;
	}

	public String getCodiceCertificatoFD() {
		return codiceCertificatoFD;
	}

	public void setCodiceCertificatoFD(String codiceCertificatoFD) {
		this.codiceCertificatoFD = codiceCertificatoFD;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDescrizioneAnagrafica() {
		return descrizioneAnagrafica;
	}

	public void setDescrizioneAnagrafica(String descrizioneAnagrafica) {
		this.descrizioneAnagrafica = descrizioneAnagrafica;
	}

	@Override
	public String toString() {
		return "FlussoFirmaDigitale [id=" + id + ", codiceIstituto=" + codiceIstituto + ", codiceCertificatoFD="
				+ codiceCertificatoFD + ", issuer=" + issuer + ", serialNumber=" + serialNumber
				+ ", descrizioneAnagrafica=" + descrizioneAnagrafica + "]";
	}
	
	

}
