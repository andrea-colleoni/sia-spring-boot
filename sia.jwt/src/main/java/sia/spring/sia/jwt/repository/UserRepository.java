package sia.spring.sia.jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.spring.sia.jwt.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByToken(String token);

}
