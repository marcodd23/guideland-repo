package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LANGUAGES")
public class Language implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5833993026138086795L;
	
	@Id
	@Column(name = "language_id")
	@GeneratedValue
	private Long languageId;
	
	private String language;

	private String level;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_fk", referencedColumnName="user_id")
	private User user;

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
