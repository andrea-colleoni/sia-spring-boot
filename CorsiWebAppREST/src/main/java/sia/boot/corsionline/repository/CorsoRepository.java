package sia.boot.corsionline.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sia.boot.corsionline.model.Corso;

@RepositoryRestResource(path="corsi")
public interface CorsoRepository extends PagingAndSortingRepository<Corso, String> {

}
