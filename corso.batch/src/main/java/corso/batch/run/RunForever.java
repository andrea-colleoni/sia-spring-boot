package corso.batch.run;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import corso.batch.config.AppConfig;
import corso.batch.config.BatchConfig;

public class RunForever {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, BatchConfig.class);
		
		ctx.getBean(ScheduleJob.class);
		
		System.out.println("Premi un tasto per terminare...");
		System.in.read();
		
		ctx.close();
	}

}
