package it.guideland.app.security;

import java.util.List;

import it.guideland.app.model.Role;

public class TokenData {

	private long id;
	private String usernameEmail;
	private String password;
	private long expirationTime;
	//private List<Role> roles;
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsernameEmail() {
		return usernameEmail;
	}
	public void setUsernameEmail(String usernameEmail) {
		this.usernameEmail = usernameEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
