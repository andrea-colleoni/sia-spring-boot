package sia.spring.sia.jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.spring.sia.jwt.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
