package sia.spring.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	/*
	@Autowired
	private DataSource dataSource;
	*/
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth
			.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder)
			.withUser("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER")			
				.and()
			.withUser("admin")
				.password(passwordEncoder.encode("admin"))
				//.password("{bcrypt}admin")
				.roles("ADMIN", "USER")
				.and()
			.withUser("public")
				.password(passwordEncoder.encode("public"))
				//.password("{noop}public")
				.roles()
			;
	}
	*/
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth
			.jdbcAuthentication()
			.passwordEncoder(passwordEncoder)
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from users")
			.authoritiesByUsernameQuery("select username, authority from authorities where username=?")
			.withUser("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER")			
				.and()
			.withUser("admin")
				.password(passwordEncoder.encode("admin"))
				//.password("{bcrypt}admin")
				.roles("ADMIN", "USER")
				.and()
			.withUser("public")
				.password(passwordEncoder.encode("public"))
				//.password("{noop}public")
				.roles()
			;
	}
	*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(getPasswordEncoder())
			;
	}
	
}
