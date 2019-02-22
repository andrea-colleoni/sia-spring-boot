package sia.bootdatajpa.repository;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import sia.bootdatajpa.model.Persona;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonaRepositoryTest {
	
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
			System.out.println(p);
		}
	}

	@Test
	public void testFindAllPageable() {
		long numPersone = personaRepository.count();
		Pageable pageable = PageRequest.of((int)(numPersone / 2) - 1, 2, Sort.by("altezzaInCm").ascending());
		Iterable<Persona> persone = personaRepository.findAll(pageable);
		
		for(Persona p : persone) {
			System.out.println(p);
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
	
	@Test
	public void testFindBy() {
		Iterable<Persona> persone = personaRepository.findByCognome("Rossi");
		for(Persona p : persone) {
			System.out.println(p);
		}
	}

}
