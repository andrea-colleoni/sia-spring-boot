package sia.bootdatajpa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sia.bootdatajpa.model.Persona;

@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {
	
	List<Persona> findByCognome(String cognome);

}
