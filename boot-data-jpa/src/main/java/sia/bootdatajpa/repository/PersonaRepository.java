package sia.bootdatajpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import sia.bootdatajpa.model.Persona;

@RepositoryRestResource(collectionResourceRel="persone", path="persone")
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {
	
	@Async
	Future<List<Persona>> findByCognome(String cognome);
	
	List<Persona> findByAltezzaInCmBetween(int min, int max);
	
	@Query("select max(p.id) from Persona p")
	Long getMaxId();
	
	@Query(value= "select * from persona", nativeQuery=true)
	List<Persona> queryNativa();
	
	@Query(value = "select * from persona where numero_scarpe = ?1 limit 1", nativeQuery=true)
	Optional<Persona> primaPersonaConScarpe(int numero);

	Stream<Persona> findByPesoInKgGreaterThan(float pesoMinimo);
}
