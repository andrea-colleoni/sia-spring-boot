package corso.batch.etl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class DecompressionTasklet implements Tasklet {
	
	private Resource inputResource;
	private String targertDir;
	private String targetFileName;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ZipInputStream zis = new ZipInputStream(inputResource.getInputStream());
		
		File targetDir = new File(targertDir);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		File targetFile = new File(targetDir, targetFileName);
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

	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}
	
	public void setTargertDir(String targertDir) {
		this.targertDir = targertDir;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
}
