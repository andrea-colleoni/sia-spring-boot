package sia.batch.sales.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class CommitLogger  {

	private static Logger log = LoggerFactory.getLogger(CommitLogger.class);

	@AfterChunk
	public void afterChunk(ChunkContext context) {
		StepExecution se = context.getStepContext().getStepExecution();
		log.info("new commit => #" + se.getCommitCount() + " - reades #" + se.getReadCount());
	}

}
