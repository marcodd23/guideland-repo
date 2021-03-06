package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUPPORTED_LANGUAGES")
public class SupportedLanguage implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2126066992088374934L;
	
	@Id
	@Column(name="lang_id")
	private String langId;

	@Column(unique=true)
	private String languageName;

	public SupportedLanguage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupportedLanguage(String langId, String languageName) {
		super();
		this.langId = langId;
		this.languageName = languageName;
	}
	
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	
}
