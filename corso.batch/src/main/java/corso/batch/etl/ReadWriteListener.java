package corso.batch.etl;

import javax.batch.api.chunk.listener.ChunkListener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileParseException;

import corso.batch.model.Prodotto;

public class ReadWriteListener { // implements StepListener {

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before step => " + stepExecution.getStepName());
	}
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("after step => " + stepExecution.getStepName());
		return ExitStatus.COMPLETED;
		
	}
	
	@BeforeChunk
	public void beforeChunk(ChunkContext context) {
		System.out.println("before chunk = > " + context.isComplete());
	}
	
	@AfterChunk
	public void afterChunk(ChunkContext context) {
		System.out.println("after chunk = > " + context.isComplete());
	}
	
	@AfterChunkError
	public void afterChunkError(ChunkContext context) {
		System.out.println("after chunk error = > " + context.isComplete());
	}
	
	@BeforeRead
	public void beforeRead() {
		System.out.println("before read " );
	}
	
	@AfterRead
	public void afterRead(Prodotto item) {
		System.out.println("after read = > " + item.toString());
	}
	
//	@OnReadError
//	public void onReadError(Exception ex) {
//		System.out.println("read error ");
//		if (ex instanceof FlatFileParseException) {
//			FlatFileParseException fex = (FlatFileParseException)ex;
//			System.out.println(fex.getInput());
//		}
//		ex.printStackTrace();
//	}

//	public void beforeChunk() throws Exception {
//		System.out.println("before chunk IF");
//		
//	}
//
//	public void onError(Exception ex) throws Exception {
//		System.out.println("error chunk IF");
//		
//	}
//
//	public void afterChunk() throws Exception {
//		System.out.println("after chunk IF");
//		
//	}
}
