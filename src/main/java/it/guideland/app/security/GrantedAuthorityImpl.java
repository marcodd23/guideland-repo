package it.guideland.app.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2115121864761726783L;
	
	private String role;	

	public GrantedAuthorityImpl(String role) {
		super();
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}
}
