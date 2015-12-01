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
	private boolean notLocked;
	private boolean notExpired;
	private boolean enabled;
	
	public UserDetailsImpl(User user, List<GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
		this.notLocked = user.getAccount().isNotLocked();
		this.notExpired = user.getAccount().isNotExpired();
		this.enabled = user.getAccount().isEnabled();
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
		return user.getAccount().getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.notExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.notLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.notExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
