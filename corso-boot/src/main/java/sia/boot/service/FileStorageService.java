package sia.boot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sia.boot.app.FileUploadProperties;

@Service
public class FileStorageService {

	@Autowired
	private FileUploadProperties fileUploadProperties;

	private Path fileStorageLocation;

	public String storeFile(MultipartFile file) throws IOException {

		fileStorageLocation = Paths.get(fileUploadProperties.getFolder());

		Files.createDirectories(fileStorageLocation);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Path targetLocation = fileStorageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}

	public String storeBytes(byte[] bytes) throws IOException {

		fileStorageLocation = Paths.get(fileUploadProperties.getFolder());

		Files.createDirectories(fileStorageLocation);

		String fileName = Files.createTempFile("upl_", ".zzz").getFileName().toString();

		Path targetLocation = fileStorageLocation.resolve(fileName);
		Files.copy(new ByteArrayInputStream(bytes), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}

	public Resource loadFile(String fileName) throws Exception {
		fileStorageLocation = Paths.get(fileUploadProperties.getFolder());
		
		Path pdfPath = fileStorageLocation.resolve("Programma_Java_Corso2018_2019.pdf").normalize();
		Resource resource = new UrlResource(pdfPath.toUri());
		if (resource.exists()) {
			return resource;
		}
		throw new Exception();
	}

}
