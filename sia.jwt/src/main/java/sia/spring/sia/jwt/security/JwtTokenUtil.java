package sia.spring.sia.jwt.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sia.spring.sia.jwt.model.Role;
import sia.spring.sia.jwt.model.User;

@Component
public class JwtTokenUtil {
	
	String secret = "secret";
	
	public User getUserDatils(String token) {
		if (token == null) {
			return null;
		}
		
		Claims claims = getClaimsFromToken(token);
		List<Role> ruoli = ((List<String>)claims.get("roles"))
				.stream()
				.map(s -> {
					Role r = new Role();
					r.setRuolo(s);
					return r;
				})
				.collect(Collectors.toList());
		
		User u = new User();
		u.setUsername(claims.getSubject());
		u.setRuoli(ruoli);
		return u;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		return claims;
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		Claims claims = getClaimsFromToken(token);
		expiration = claims.getExpiration();
		return expiration;
	}
	
	public Boolean isTokenExpired(String token) {
		Date expire = getExpirationDateFromToken(token);
		return expire.before(new Date());
	}
	public Boolean validateToken(String token, User user) {
		Claims claims = getClaimsFromToken(token);
		return claims.getSubject().equals(user.getUsername()) && ! isTokenExpired(token);
	}

	public String generateToken(UserDetails ud) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", ud.getUsername());
		claims.put("iat", new Date()); // data di creazione del token
		claims.put("roles", ud.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()));
		claims.put("isEnabled", ud.isEnabled());
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 ora
				.signWith(SignatureAlgorithm.HS256, "secret")
				.compact(); // fa il build del token
	}

}
