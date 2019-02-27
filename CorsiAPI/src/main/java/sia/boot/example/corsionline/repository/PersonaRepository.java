package sia.boot.example.corsionline.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sia.boot.example.corsionline.model.Persona;

@RepositoryRestResource(path="persone")
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {

}
