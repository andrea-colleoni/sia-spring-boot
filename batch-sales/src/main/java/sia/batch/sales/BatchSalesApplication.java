package sia.batch.sales;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

// @SpringBootApplication //(exclude = {BatchAutoConfiguration.class })
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BatchSalesApplication {
	
	@Autowired
	private JobLauncher jl;
	
	@Autowired
	private Job job;

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(BatchSalesApplication.class, args);
		// SpringApplication.run(BatchSalesApplication.class, args);
		
		BatchSalesApplication app = context.getBean(BatchSalesApplication.class);
		app.run();
	}
	
	// @Scheduled(initialDelay=0, fixedDelay=360000)
	public void run() {
		long millis = System.currentTimeMillis();
		try {
			jl.run(job, new JobParametersBuilder()
					.getNextJobParameters(job)
					// .addString("flatFile", "file:./out/10000 Sales Records.csv")
					.addString("zipFile", "file:./in/10000-Sales-Records.zip")
					.addString("targetDir", "./out")
					//.addLong("timestamp", System.currentTimeMillis())
					.toJobParameters());
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - millis);
	}
}
