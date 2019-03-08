package sia.batch.sales.config;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import sia.batch.sales.etl.CommitLogger;
import sia.batch.sales.etl.DecompressionTasklet;
import sia.batch.sales.etl.FileSplitter;
import sia.batch.sales.etl.FlussoFirmaJpaWriter;
import sia.batch.sales.etl.MultiSalesItemReader;
import sia.batch.sales.etl.SalesFlatFileReader;
import sia.batch.sales.etl.SalesJpaWriter;
import sia.batch.sales.etl.SalesRecordProcessor;
import sia.batch.sales.etl.SynchronizedItemReader;
import sia.batch.sales.model.FlussoFirmaDigitale;
import sia.batch.sales.model.SalesRecord;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jbf;

	@Autowired
	private StepBuilderFactory sbf;
	
	@Autowired
	private ResourcePatternResolver resourcePatternResolver;

	@Bean
	public Job importSales() throws IOException {
		Job j = jbf.get("importSales")
				.incrementer(new RunIdIncrementer())
				.start(mainFlow())
				.end()
				.build();
		return j;
	}

	private Flow mainFlow() throws IOException {
		FlowBuilder<Flow> fb = new FlowBuilder<Flow>("mainFlow");

		Flow f = fb
				.start(decompress())
				.next(sbf.get("split")
				.tasklet(new FileSplitter()).build())
				.next(salesPartionStep())
//				.start(flussoFirmaChunkStep())
				.build();
		return f;
	}

	private Step decompress() {
		Step s = sbf.get("decompress")
				.tasklet(decompressTasklet(null, null))
				// .listener(new SalesStepListener())
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
				.<SalesRecord, SalesRecord>chunk(200)
				.reader(salesReader(null))
				.processor(salesProcessor())
				.writer(salesWriter())
				//.listener(new CommitLogger())
				.build();
		return s;
	}
	
	private Step salesPartionStep() throws IOException {
		Step s = sbf.get("salesPartitionStep")
				.partitioner("salesChunkStep", partitioner())
				.step(salesChunkStep())
				.taskExecutor(taskExecutor())
				.build();
		return s;
	}

	private Partitioner partitioner() throws IOException {
		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
		Resource[] resources;
		try {
			resources = resourcePatternResolver.getResources("file:./out/*_*.csv");
		} catch (IOException e) {
			throw new RuntimeException("I/O problems when resolving the input file pattern.",e);
		}
		partitioner.setResources(resources);
		return partitioner;
	}

	private TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setConcurrencyLimit(10);
		return taskExecutor;
	}

//	private Step flussoFirmaChunkStep() throws MalformedURLException {
//		Step s = sbf.get("fussoFirmaChunkStep")
//				.<FlussoFirmaDigitale, FlussoFirmaDigitale> chunk(100)
//				.reader(flussoFirmaReader())
//				.writer(flussoFirmaWriter())
//				.listener(new CommitLogger())
//				.build();
//		return s;
//	}

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
	
	@Bean
	@StepScope
	public SalesFlatFileReader salesReader(@Value("#{stepExecutionContext[fileName]}") Resource flatFile) {
		SalesFlatFileReader del = new SalesFlatFileReader();
		del.setResource(flatFile);
		return del;
	}

//	@Bean
//	@StepScope
//	public SynchronizedItemReader salesReader(@Value("#{jobParameters['targetDir']}") Resource flatFile) {
//		SynchronizedItemReader sir = new SynchronizedItemReader();
//		SalesFlatFileReader del = new SalesFlatFileReader();
//		del.setResource(flatFile);
//		sir.setDelegate(del);
//		return sir;
//	}

//	@Bean
//	@StepScope
//	public FlussoFirmaFlatFileReader flussoFirmaReader() throws MalformedURLException {
//		FlussoFirmaFlatFileReader sir = new FlussoFirmaFlatFileReader();
//		sir.setResource(new UrlResource("file:./in/TJSFDCS Flusso Certificati Firma Digitale.txt"));
//		return sir;
//	}

//	@Bean
//	@StepScope
//	public MultiSalesItemReader multiSalesReader(@Value("#{jobParameters['targetDir']}") String targetDir) {
//		MultiSalesItemReader sir = new MultiSalesItemReader(targetDir);
//		return sir;
//	}

}
