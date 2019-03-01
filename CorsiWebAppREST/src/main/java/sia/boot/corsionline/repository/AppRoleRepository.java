package sia.boot.corsionline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.boot.corsionline.model.AppRole;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRole, String> {

}
