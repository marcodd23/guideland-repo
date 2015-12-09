package it.guideland.app.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.guideland.app.model.Role;
import it.guideland.app.model.User;
import it.guideland.app.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<< UserDetailsServiceImpl.loadUserByUsername >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		User user;
		user = userRepo.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = getAuthorities(user);
		return new UserDetailsImpl(user, authorities);
	}

	
	private List<GrantedAuthority> getAuthorities(User user) {
		Role role = user.getRole();
		List<GrantedAuthority> authorities = new ArrayList<>(1);
		if (role != null) {
			authorities.add(new GrantedAuthorityImpl(role.getRolename()));
		}
		return authorities;
	}
	
/*	private List<GrantedAuthority> getAuthorities(User user) {
		List<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>(roles == null ? 0 : roles.size());
		if (roles != null) {
			for (Role role : roles) {
				authorities.add(new GrantedAuthorityImpl(role.getRolename()));
			}
		}
		return authorities;
	}*/

}
