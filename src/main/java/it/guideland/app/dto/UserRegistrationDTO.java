package it.guideland.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.guideland.app.model.Language;
import it.guideland.app.utils.CustomDateDeserializer;

public class UserRegistrationDTO {

	private String name;
	private String surname;
	private String username;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	private List<String> languages = new ArrayList<>();
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date bornDate;
	private String city;
	private String telephone;
	private int hourlyRate;
	private String description;
 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
