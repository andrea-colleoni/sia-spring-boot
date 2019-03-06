package corso.batch.etl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class DecompressionTasklet implements Tasklet {
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Resource inputResource = new ClassPathResource("./in/dati.zip");
		ZipInputStream zis = new ZipInputStream(inputResource.getInputStream());
		
		File targetDir = new File("./out");
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		File targetFile = new File(targetDir, "dati.csv");
		while(zis.getNextEntry() != null) {
			targetFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(targetFile);
			IOUtils.copy(zis, fos);
			fos.flush();
			fos.close();
		}
		zis.close();
		return RepeatStatus.FINISHED;
	}
}
