package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Language implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5833993026138086795L;
	
	@Id
	@Column(name = "LANGUAGE_ID")
	@GeneratedValue
	private Long languageId;
	private String name;
	private String level;
	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;
	
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
