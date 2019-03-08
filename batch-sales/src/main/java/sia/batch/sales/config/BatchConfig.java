package sia.batch.sales.config;

import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import sia.batch.sales.etl.CommitLogger;
import sia.batch.sales.etl.DecompressionTasklet;
import sia.batch.sales.etl.FlussoFirmaFlatFileReader;
import sia.batch.sales.etl.FlussoFirmaJpaWriter;
import sia.batch.sales.etl.MultiSalesItemReader;
import sia.batch.sales.etl.SalesJpaWriter;
import sia.batch.sales.etl.SalesRecordProcessor;
import sia.batch.sales.model.FlussoFirmaDigitale;
import sia.batch.sales.model.SalesRecord;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private StepBuilderFactory sbf;
	
	@Bean
	public Job importSales() throws MalformedURLException {
		Job j = jbf.get("importSales")
				.start(mainFlow())
				.end()
				.build();
		return j;
	}

	private Flow mainFlow() throws MalformedURLException {
		FlowBuilder<Flow> fb = new FlowBuilder<Flow>("mainFlow");
		
		Flow f = fb
//				.start(decompress())
//				.next(salesChunkStep())
				.start(flussoFirmaChunkStep())
				.build();
		return f;
	}
	
	private Step decompress() {
		Step s = sbf.get("decompress")
				.tasklet(decompressTasklet(null, null))
				//.listener(new SalesStepListener())
				.build();
		return s;
	}

	@Bean
	@StepScope
	public Tasklet decompressTasklet(@Value("#{jobParameters['zipFile']}") Resource inputResource,
			@Value("#{jobParameters['targetDir']}") String targetDir) {
		DecompressionTasklet t = new DecompressionTasklet();
		t.setInputResource(inputResource);
		t.setTargertDir(targetDir);
		return t;
	}

	private Step salesChunkStep() {
		Step s = sbf.get("salesChunkStep")
				.<SalesRecord, SalesRecord> chunk(100)
				.reader(multiSalesReader(null))
				.processor(salesProcessor())
				.writer(salesWriter())
				.listener(new CommitLogger())
				.build();
		return s;
	}
	
	private Step flussoFirmaChunkStep() throws MalformedURLException {
		Step s = sbf.get("fussoFirmaChunkStep")
				.<FlussoFirmaDigitale, FlussoFirmaDigitale> chunk(100)
				.reader(flussoFirmaReader())
				.writer(flussoFirmaWriter())
				.listener(new CommitLogger())
				.build();
		return s;
	}
	
	@Bean
	public SalesJpaWriter salesJpaWriter() {
		return new SalesJpaWriter();
	}
	
	@Bean
	public FlussoFirmaJpaWriter flussoFirmaJpaWriter() {
		return new FlussoFirmaJpaWriter();
	}

	private ItemWriter<? super SalesRecord> salesWriter() {
		return salesJpaWriter();
	}

	private ItemWriter<? super FlussoFirmaDigitale> flussoFirmaWriter() {
		return flussoFirmaJpaWriter();
	}
	
	private ItemProcessor<? super SalesRecord, ? extends SalesRecord> salesProcessor() {
		return new SalesRecordProcessor();
	}

//	@Bean
//	@StepScope
//	public SalesFlatFileReader salesReader(@Value("#{jobParameters['targetDir']}") Resource flatFile) {
//		SalesFlatFileReader sir = new SalesFlatFileReader();
//		sir.setResource(flatFile);
//		return sir;
//	}
	
	@Bean
	@StepScope
	public FlussoFirmaFlatFileReader flussoFirmaReader() throws MalformedURLException {
		FlussoFirmaFlatFileReader sir = new FlussoFirmaFlatFileReader();
		sir.setResource(new UrlResource("file:./in/TJSFDCS Flusso Certificati Firma Digitale.txt"));
		return sir;
	}
	
	@Bean
	@StepScope
	public MultiSalesItemReader multiSalesReader(@Value("#{jobParameters['targetDir']}") String targetDir) {
		MultiSalesItemReader sir = new MultiSalesItemReader(targetDir);
		return sir;
	}

}
