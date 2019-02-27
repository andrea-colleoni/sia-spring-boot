package sia.spring.websecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.spring.websecurity.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, String> {

	List<ApplicationUser> findByUsername(String username);
}
