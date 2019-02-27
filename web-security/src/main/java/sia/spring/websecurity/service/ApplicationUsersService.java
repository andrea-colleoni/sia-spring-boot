package sia.spring.websecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sia.spring.websecurity.repository.ApplicationUserRepository;

@Service
public class ApplicationUsersService implements UserDetailsService {
	
	@Autowired
	private ApplicationUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}

}
