package it.guideland.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.guideland.app.model.Language;
import it.guideland.app.utils.CustomDateDeserializer;

public class UserRegistrationDTO {

	private String name;
	private String surname;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	//private List<String> languages = new ArrayList<>();
	private Map<String, String> languages = new HashMap<>();
	
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date bornDate;
	private String city;
	private String telephone;
	private String hourlyRate;
	private String description;
	private String parentInterest;
	private String interest;
	private String interestDescription;
	private String interestScore;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Map<String, String> getLanguages() {
		return languages;
	}
	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentInterest() {
		return parentInterest;
	}
	public void setParentInterest(String parentInterest) {
		this.parentInterest = parentInterest;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getInterestDescription() {
		return interestDescription;
	}
	public void setInterestDescription(String interestDescription) {
		this.interestDescription = interestDescription;
	}
	public String getInterestScore() {
		return interestScore;
	}
	public void setInterestScore(String interestScore) {
		this.interestScore = interestScore;
	}
}
