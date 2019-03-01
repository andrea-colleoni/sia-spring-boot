package sia.boot.corsionline.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.boot.corsionline.model.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {

	List<AppUser> findByUsername(String username);
}
