package sia.spring.websecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.spring.websecurity.model.ApplicationRole;

@Repository
public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, String> {

}
