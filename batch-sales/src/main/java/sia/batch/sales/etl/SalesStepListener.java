package sia.batch.sales.etl;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;

public class SalesStepListener {
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		int numeroFiles = (Integer)stepExecution.getExecutionContext().get("numeroFiles");
		System.out.println(numeroFiles);
		return ExitStatus.COMPLETED;
	}
}
