package it.guideland.app.dto;

import java.util.List;

import it.guideland.app.model.Role;

public class TokenData {

	private long id;
	private String username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
