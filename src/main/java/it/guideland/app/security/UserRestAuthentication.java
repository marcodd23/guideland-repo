package it.guideland.app.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserRestAuthentication extends AbstractAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8794293627074900342L;
	
	private TokenData tokenData;
	private boolean authenticated = true;
	
	public UserRestAuthentication(Collection<? extends GrantedAuthority> authorities, TokenData tokenData) {
		super(authorities);
		this.tokenData = tokenData;
	}
	@Override
	public Object getCredentials() {
		return tokenData.getUsername();
	}
	@Override
	public Object getPrincipal() {
		return tokenData.getUsername();
	}
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
	
}
