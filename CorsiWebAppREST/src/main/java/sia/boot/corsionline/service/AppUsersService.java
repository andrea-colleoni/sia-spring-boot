package sia.boot.corsionline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sia.boot.corsionline.repository.AppUserRepository;

@Service
public class AppUsersService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}

}
