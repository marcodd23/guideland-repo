package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4914191730785149204L;
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private Long userId;
	private String name;
	private String surname;
	private Date bornDay;
	private String sex;
	private String email;
	private String mobileNumber;
	private String skype;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	@JoinColumn(name="IMAGE_ID", nullable = true)
	Photo profilePhoto;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public Date getBornDay() {
		return bornDay;
	}

	public void setBornDay(Date bornDay) {
		this.bornDay = bornDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public Photo getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Photo profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
}
