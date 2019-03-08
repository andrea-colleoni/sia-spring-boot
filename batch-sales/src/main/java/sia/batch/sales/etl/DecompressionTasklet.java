package sia.batch.sales.etl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
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

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ZipInputStream zis = new ZipInputStream(inputResource.getInputStream());
		
		File targetDir = new File(targertDir);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		for(File f : targetDir.listFiles()) {
			f.delete();
		}
		ZipEntry ze = zis.getNextEntry();
		int count = 0;
		while(ze != null) {
			count++;
			File targetFile = new File(targetDir.getAbsolutePath() + "/" + ze.getName());
			targetFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(targetFile);
			IOUtils.copy(zis, fos);
			fos.flush();
			fos.close();
			ze = zis.getNextEntry();
		}
		zis.close();
		// chunkContext.getStepContext().setAttribute("numeroFiles", count);
		return RepeatStatus.FINISHED;
	}

	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}
	
	public void setTargertDir(String targertDir) {
		this.targertDir = targertDir;
	}
}
