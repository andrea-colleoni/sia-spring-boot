package sia.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sia.boot.model.Corso;
import sia.boot.repository.CorsoRepository;

@RestController
@RequestMapping(path="/corsi")
public class CorsoController {

	@Autowired
	private CorsoRepository corsoRepository;
	
	@GetMapping
	public Iterable<Corso> getCorsi() {
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
	
	@PutMapping("/{id}")
	public Corso updateCorso(@RequestBody Corso corso, @PathVariable Integer id) throws Exception {
		if (corso.getId() != id) {
			throw new Exception("gli id non cincidono");
		}
		corsoRepository.findById(id)
			.orElseThrow(Exception::new);
		return corsoRepository.save(corso);
	}
	
	
}
