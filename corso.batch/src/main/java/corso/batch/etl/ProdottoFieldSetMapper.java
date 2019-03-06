package corso.batch.etl;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import corso.batch.model.Prodotto;

public class ProdottoFieldSetMapper implements FieldSetMapper<Prodotto> {

	public Prodotto mapFieldSet(FieldSet fieldSet) throws BindException {
		Prodotto p = new Prodotto();
		p.setId(fieldSet.readString("PRODUCT_ID"));
		p.setNome(fieldSet.readString("NAME"));
		p.setDecsrizione(fieldSet.readString("DESCRIPTION"));
		p.setPrezzo(fieldSet.readBigDecimal("PRICE"));
		return p;
	}
}
