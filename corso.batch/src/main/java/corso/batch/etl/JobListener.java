package corso.batch.etl;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class JobListener implements JobExecutionListener {

	@BeforeJob
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("befor job");

	}

	@AfterJob
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after job");
	}

}
