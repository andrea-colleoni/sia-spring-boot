package sia.batch.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sia.batch.sales.model.SalesRecord;

@Repository
public interface SalesRecordRepository extends JpaRepository<SalesRecord, Long> {

}
