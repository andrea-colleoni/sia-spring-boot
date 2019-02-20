package sia.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.boot.model.Corso;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Integer> {

}
