package sia.jpa.rep2;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sia.jpa.db2.Entita2;

@Repository
public interface Repo2 extends PagingAndSortingRepository<Entita2, Integer> {

}
