package sia.batch.sales.etl;

import java.io.IOException;

import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import sia.batch.sales.model.SalesRecord;

public class MultiSalesItemReader extends MultiResourceItemReader<SalesRecord> {
	
	
	public MultiSalesItemReader(String targetDir) {
		super();
		Resource[] resources;
		try {
			resources = new PathMatchingResourcePatternResolver().getResources("file:" + targetDir + "/*_*.csv");
			this.setResources(resources);
			this.setDelegate(new SalesFlatFileReader());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
