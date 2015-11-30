package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persistent_logins")
public class PersistentLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1492252791513786952L;
	
	@Id
	@Column(name = "series")
	private String series;
	
	@Column(name="username")
	private String username;
	
	@Column(name="token")
	private String token;
	
	@Column(name="last_used timestamp")
	private String lastUsedTimestamp;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastUsedTimestamp() {
		return lastUsedTimestamp;
	}

	public void setLastUsedTimestamp(String lastUsedTimestamp) {
		this.lastUsedTimestamp = lastUsedTimestamp;
	}
	
}
