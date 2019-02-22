package sia.bootdatajpa.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sia.bootdatajpa.model.Persona;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonaRepositoryTest {
	
	static Logger log = LogManager.getLogger(PersonaRepositoryTest.class);
	
	@Autowired
	private PersonaRepository personaRepository;

	@Test
	public void testFindAllSort() {
		Iterable<Persona> persone = personaRepository.findAll(
				Sort
					.by("numeroScarpe")
					.descending()
				);
		
		for(Persona p : persone) {
			log.info(p);
		}
	}

	@Test
	public void testFindAllPageable() {
		long numPersone = personaRepository.count();
		Pageable pageable = PageRequest.of((int)(numPersone / 2) - 1, 2, Sort.by("altezzaInCm").ascending());
		Iterable<Persona> persone = personaRepository.findAll(pageable);
		
		for(Persona p : persone) {
			log.info(p);
		}
	}

	@Test
	public void testSave() {
		Persona p = new Persona();
		p.setNome("Anna");
		p.setCognome("Neri");
		p.setNumeroScarpe(37);
		p.setAltezzaInCm(168);
		p.setPesoInKg(56.0F);
		p.setDataNascita(new Date());
		
		personaRepository.save(p);
	}
	
	@Test(expected = Exception.class)
	public void testById() throws Exception {
		Persona p = personaRepository
					.findById(7)
					.orElseThrow(() -> new Exception());
	}
	
	@Test
	public void testScarpe() throws Exception {
		Persona p = personaRepository.primaPersonaConScarpe(43)
				.orElseThrow(() -> new Exception());
		assertNotNull(p);
		p = personaRepository
				.primaPersonaConScarpe(50)
				.orElse(null);
		assertNull(p);
	}
	
	@Test
	public void testFindBy() throws InterruptedException, ExecutionException {
		List<Persona> persone = personaRepository.findByCognome("Rossi").get();
		for(Persona p : persone) {
			log.info(p);
		}
		
		persone = personaRepository.findByAltezzaInCmBetween(179, 182);
		for(Persona p : persone) {
			log.info(p);
		}
		
		log.info(personaRepository.getMaxId());
		persone = personaRepository.queryNativa();
		for(Persona p : persone) {
			log.info(p);
		}
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testFindAllPesanti() {
		personaRepository.findByPesoInKgGreaterThan(50.0F)
			.parallel()
			.forEach(p -> {
//				p.setAltezzaInCm(p.getAltezzaInCm() + 1);
//				personaRepository.save(p);
				log.info(p);
			});
	}
	
	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		Future<List<Persona>> persone = personaRepository.findByCognome("Rossi");
		log.info("prima...");
		for(Persona p : persone.get()) {
			log.info(p);
		}
		log.info("finito.");
	}
}
