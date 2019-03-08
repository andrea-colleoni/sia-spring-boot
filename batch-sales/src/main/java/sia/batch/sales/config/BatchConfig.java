package sia.batch.sales.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import sia.batch.sales.etl.SalesFlatFileReader;
import sia.batch.sales.model.SalesRecord;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private StepBuilderFactory sbf;
	
	@Bean
	public Job importSales() {
		Job j = jbf.get("importSales")
				.start(mainFlow())
				.end()
				.build();
		return j;
	}

	private Flow mainFlow() {
		FlowBuilder<Flow> fb = new FlowBuilder<Flow>("mainFlow");
		
		Flow f = fb
				.start(salesChunkStep())
				.build();
		return f;
	}

	private Step salesChunkStep() {
		Step s = sbf.get("salesChunkStep")
				.<SalesRecord, SalesRecord> chunk(100)
				.reader(salesReader(null))
				.processor(salesProcessor())
				.writer(salesWriter())
				.build();
		return s;
	}

	private ItemWriter<? super SalesRecord> salesWriter() {
		return null;
	}

	private ItemProcessor<? super SalesRecord, ? extends SalesRecord> salesProcessor() {
		return null;
	}

	private ItemReader<? extends SalesRecord> salesReader(Resource flatFile) {
		SalesFlatFileReader sir = new SalesFlatFileReader();
		sir.setResource(flatFile);
		return sir;
	}

}
