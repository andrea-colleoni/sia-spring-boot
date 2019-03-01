package sia.boot.corsionline.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sia.boot.corsionline.model.Edizione;

@RepositoryRestResource(path="edizioni")
public interface EdizioneRepository extends PagingAndSortingRepository<Edizione, Integer> {

}
