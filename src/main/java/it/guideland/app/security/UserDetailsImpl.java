package it.guideland.app.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.guideland.app.model.User;

public class UserDetailsImpl implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2593509644290219635L;
	
	private User user;
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user, List<GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
/*	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> result = new ArrayList<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			result.add(new GrantedAuthorityImpl(role.getRole()));
		}
		return result;
	}*/

	@Override
	public String getPassword() {
		return user.getAccount().getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getAccount().getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.getAccount().isEnabled();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
