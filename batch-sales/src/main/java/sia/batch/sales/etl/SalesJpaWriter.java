package sia.batch.sales.etl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import sia.batch.sales.model.SalesRecord;
import sia.batch.sales.repository.SalesRecordRepository;

public class SalesJpaWriter implements ItemWriter<SalesRecord> {

	@Autowired
	private SalesRecordRepository srr;

	@Override
	public void write(List<? extends SalesRecord> items) throws Exception {
		srr.saveAll(items);
	}
}
