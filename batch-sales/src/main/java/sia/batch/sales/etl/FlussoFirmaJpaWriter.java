package sia.batch.sales.etl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import sia.batch.sales.model.FlussoFirmaDigitale;
import sia.batch.sales.repository.FlussoFirmaRepository;

public class FlussoFirmaJpaWriter implements ItemWriter<FlussoFirmaDigitale> {

	@Autowired
	private FlussoFirmaRepository ffr;

	@Override
	public void write(List<? extends FlussoFirmaDigitale> items) throws Exception {
		ffr.saveAll(items);
	}
}
