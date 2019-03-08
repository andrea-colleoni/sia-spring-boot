package sia.batch.sales.etl;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;

import sia.batch.sales.model.FlussoFirmaDigitale;

public class FlussoFirmaFlatFileReader extends FlatFileItemReader<FlussoFirmaDigitale>  {

	public FlussoFirmaFlatFileReader() {
		super();
		DefaultLineMapper<FlussoFirmaDigitale> lineMapper = new DefaultLineMapper<FlussoFirmaDigitale>();
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setNames(new String[] { "codiceIstituto","codiceCertificatoFD","issuer","serialNumber","descrizioneAnagrafica", "filler" });
		tokenizer.setColumns(new Range(1, 5), new Range(6, 12), new Range(13, 262), new Range(263, 390), new Range(391, 420), new Range(421, 509));
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new FlussoFirmaDigitaleFieldSetMapper());
		this.setLineMapper(lineMapper);
		this.setLinesToSkip(0);
	}
}
