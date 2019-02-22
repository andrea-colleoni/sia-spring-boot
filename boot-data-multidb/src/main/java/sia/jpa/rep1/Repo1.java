package sia.jpa.rep1;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sia.jpa.db1.Entita1;

@Repository
public interface Repo1 extends PagingAndSortingRepository<Entita1, Integer> {

}
