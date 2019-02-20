package sia.boot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sia.boot.app.FtpProperties;
import sia.boot.model.Corso;
import sia.boot.model.UploadFileResponse;
import sia.boot.repository.CorsoRepository;
import sia.boot.service.FileStorageService;

@RestController
@RequestMapping(path="/corsi")
public class CorsoController {

	@Autowired
	private CorsoRepository corsoRepository;
	
	@Autowired
	private static FtpProperties ftpProps;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping
	public Iterable<Corso> getCorsi() {
		// System.out.println(ftpProps.getUrl());
		return corsoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Corso findOne(@PathVariable Integer id) throws Exception {
		return corsoRepository.findById(id)
				.orElseThrow(Exception::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Corso create(@RequestBody Corso corso) {
		return corsoRepository.save(corso);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) throws Exception {
		corsoRepository.findById(id)
			.orElseThrow(Exception::new);
		corsoRepository.deleteById(id);
	}
	
	// @PutMapping("/{id}")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Corso updateCorso(@RequestBody Corso corso, @PathVariable Integer id) throws Exception {
		/*
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(corso); // serializzazione 
		Corso c = om.readValue(json, Corso.class); // deserializzazione
		*/
		
		if (corso.getId() != id) {
			throw new Exception("gli id non coincidono");
		}
		corsoRepository.findById(id)
			.orElseThrow(Exception::new);
		return corsoRepository.save(corso);
	}
	
	// @PutMapping("/{id}")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces= {MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<Corso> printCorso(@RequestBody Corso corso, @PathVariable Integer id) throws Exception {
		Corso c = new Corso();
		c.setTitolo("Mock PDF");
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping("/uploadBanner")
	public UploadFileResponse uploadBanner(MultipartFile file) throws IOException {
		String fileName = fileStorageService.storeFile(file);
		
		UploadFileResponse res = new UploadFileResponse();
		res.setFileName(fileName);
		res.setFileType(file.getContentType());
		res.setSize(file.getSize());
		
		return res;
	}
	
	@PostMapping("/uploadBytes")
	public UploadFileResponse uploadBytes(@RequestBody byte[] bytes) throws IOException {
		String fileName = fileStorageService.storeBytes(bytes);
		
		UploadFileResponse res = new UploadFileResponse();
		res.setFileName(fileName);
		// res.setFileType(file.getContentType());
		res.setSize(bytes.length);
		
		return res;
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadPdf(@PathVariable String fileName, HttpServletRequest req) throws Exception {
		Resource res = fileStorageService.loadFile(fileName);
		
		String contentType = req.getServletContext().getMimeType(res.getFile().getAbsolutePath());
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.getFilename() + "\"")
				.body(res);
	}
	
}
