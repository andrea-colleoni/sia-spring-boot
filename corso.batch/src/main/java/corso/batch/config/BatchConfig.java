package corso.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import corso.batch.etl.DecompressionTasklet;
import corso.batch.etl.JobListener;
import corso.batch.etl.ProdottoFieldSetMapper;
import corso.batch.etl.ProductJdbcItemWriter;
import corso.batch.etl.ReadWriteListener;
import corso.batch.etl.ReadWriteSkipListener;
import corso.batch.model.Prodotto;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private StepBuilderFactory sbf;

//	@Bean
//	public JobLauncher jobLauncher() throws Exception {
//		SimpleJobLauncher sjl = new SimpleJobLauncher();
//		sjl.setJobRepository(jobRepository());
////		SimpleThreadPoolTaskExecutor taskExecutor = new SimpleThreadPoolTaskExecutor();
////		taskExecutor.setThreadCount(10);
////		sjl.setTaskExecutor(new SimpleAsyncTaskExecutor());
//		return sjl;
//	}

//	@Bean
//	public JobRepository jobRepository() throws Exception {
//		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//		factory.setDataSource(dataSource);
//		factory.setTransactionManager(transactionManager);
//		return factory.getObject();
//	}
	
	@Bean
	@StepScope
	public Tasklet decompressionTasklet(
			@Value("file:#{jobParameters['inputResource']}") Resource inputFile,
			@Value("./out") String targertDir,
			@Value("#{jobParameters['targetFileName']}") String targetFileName
			) {
		DecompressionTasklet dt = new DecompressionTasklet();
		dt.setInputResource(inputFile);
		dt.setTargertDir(targertDir);
		dt.setTargetFileName(targetFileName);
		return dt;
	}
	
	private Step decompress() {
		return sbf.get("decompress")
		.tasklet(decompressionTasklet(null, null, null))
		.build();
	}
	
	private Step readWriteProdotti() {
		return sbf.get("readWriteProdotti")
				.<Prodotto, Prodotto> chunk(100)
				.reader(reader(null))
				.writer(writer())
				.faultTolerant()
				.skipLimit(2)
				.skip(FlatFileParseException.class)
				.listener(new ReadWriteListener())
				.listener(new ReadWriteSkipListener())
				.build();
	}
	
	@Bean
	public ProductJdbcItemWriter writer() {
		ProductJdbcItemWriter w = new ProductJdbcItemWriter();
		return w;
	}

	@Bean
	@StepScope
	public FlatFileItemReader<Prodotto> reader(@Value("file:./out/#{jobParameters['targetFileName']}") Resource outputFile) {
		FlatFileItemReader<Prodotto> ffir = new FlatFileItemReader<Prodotto>();
		ffir.setResource(outputFile);
		DefaultLineMapper<Prodotto> lineMapper = new DefaultLineMapper<Prodotto>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "PRODUCT_ID","NAME","DESCRIPTION","PRICE" });
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new ProdottoFieldSetMapper());
		ffir.setLineMapper(lineMapper);
		ffir.setLinesToSkip(1);
		return ffir;
	}

	@Bean
	public Job importProdotti() {
		FlowBuilder<Flow> fb = new FlowBuilder<Flow>("mainFlow");
		
		Flow flow = fb
				.start(decompress())
				.next(readWriteProdotti())
				.build();
		
		DefaultJobParametersValidator jpv = new DefaultJobParametersValidator();
		jpv.setRequiredKeys(new String[] { "inputResource", "targetFileName" });
		jpv.setOptionalKeys(new String[] { "timestamp" });
		
		Job j = jbf.get("importProdotti")
			.listener(new JobListener())
			.validator(jpv)
			.start(flow)
			.end()
			.build();		
		return j;
	}
}
