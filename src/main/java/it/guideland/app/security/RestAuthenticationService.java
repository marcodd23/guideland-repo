package it.guideland.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import it.guideland.app.model.Role;
import it.guideland.app.model.User;

@Service
public class RestAuthenticationService {

	private static final String HEADER_TOKEN = "X-Auth-Token";

	/*
	 * @Autowired private UserRepository userRepo
	 */;

	@Autowired
	private TokenManager tokenManager;

	public void addAuthenticationToken(HttpServletResponse response, UserDetails userDetails) {

		User user = ((UserDetailsImpl) userDetails).getUser();
		/*
		 * user.setLoginExpireTime(System.currentTimeMillis() + TEN_DAYS);
		 * 
		 * //TODO potrebbe non servire salvare l'entità ..... verificare!!!
		 * userRepo.save(user);
		 */

		response.addHeader(HEADER_TOKEN, tokenManager.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_TOKEN);
		if (token != null) {
			TokenData tokenData = tokenManager.validateUserFromToken(token);
			if (tokenData != null) {
				return new UserRestAuthentication(getAuthoritiesFromTokenData(tokenData), tokenData);
			}
		}
		return null;
	}

	private Collection<GrantedAuthority> getAuthoritiesFromTokenData(TokenData tokenData) {
		List<Role> roles = tokenData.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>(roles == null ? 0 : roles.size());
		if (roles != null) {
			for (Role role : roles) {
				authorities.add(new GrantedAuthorityImpl(role.getRole()));
			}
		}
		return authorities;
	}

}
