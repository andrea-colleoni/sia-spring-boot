package sia.batch.sales.etl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FileSplitter implements Tasklet {
	
	public static void main(String[] args) throws IOException {
		FileSplitter fs = new FileSplitter();
		fs.splitTextFiles(Paths.get("./out/500000 Sales Records.csv"), 1000);
	}

	public void splitTextFiles(Path bigFile, int maxRows) throws IOException {

		int i = 1;
		try (BufferedReader reader = Files.newBufferedReader(bigFile)) {
			String line = null;
			int lineNum = 1;

			Path splitFile = Paths.get("./out/" + bigFile.getFileName() + "_" + i + ".csv");
			BufferedWriter writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);

			while ((line = reader.readLine()) != null) {

				if (lineNum > maxRows) {
					writer.close();
					lineNum = 1;
					i++;
					splitFile = Paths.get("./out/" + bigFile.getFileName() + "_" + i + ".csv");
					writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);
				}

				writer.append(line);
				writer.newLine();
				lineNum++;
			}
			writer.close();
		}
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		splitTextFiles(Paths.get("./out/10000 Sales Records.csv"), 5000);
		Files.delete(Paths.get("./out/10000 Sales Records.csv"));
		return RepeatStatus.FINISHED;
	}

}
