package sia.batch.sales.etl;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import sia.batch.sales.model.FlussoFirmaDigitale;

public class FlussoFirmaDigitaleFieldSetMapper implements FieldSetMapper<FlussoFirmaDigitale> {

	@Override
	public FlussoFirmaDigitale mapFieldSet(FieldSet fieldSet) throws BindException {
		// "codiceIstituto","codiceCertificatoFD","issuer","serialNumber","descrizioneAnagrafica"
		FlussoFirmaDigitale ffd = new FlussoFirmaDigitale();
		ffd.setCodiceIstituto(fieldSet.readString("codiceIstituto"));
		ffd.setCodiceCertificatoFD(fieldSet.readString("codiceCertificatoFD"));
		ffd.setIssuer(fieldSet.readString("issuer"));
		ffd.setSerialNumber(fieldSet.readString("serialNumber"));
		ffd.setDescrizioneAnagrafica(fieldSet.readString("descrizioneAnagrafica"));
		return ffd;
	}

}
