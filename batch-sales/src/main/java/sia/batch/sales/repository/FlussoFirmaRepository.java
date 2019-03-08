package sia.batch.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sia.batch.sales.model.FlussoFirmaDigitale;

@Repository
public interface FlussoFirmaRepository extends JpaRepository<FlussoFirmaDigitale, Integer> {

}
