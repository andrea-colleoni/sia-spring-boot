package sia.batch.sales.etl;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import sia.batch.sales.model.SalesRecord;

public class SalesFlatFileReader extends FlatFileItemReader<SalesRecord> {

	public SalesFlatFileReader() {
		super();
		DefaultLineMapper<SalesRecord> lineMapper = new DefaultLineMapper<SalesRecord>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "region","country","itemType","salesChannel","orderPriority",
				"orderDate","orderID","shipDate","unitsSold","unitPrice","unitCost","totalRevenue","totalCost","totalProfit" });
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new SalesRecordFieldSetMapper());
		this.setLineMapper(lineMapper);
		this.setLinesToSkip(1);
	}
}
