package it.guideland.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Column(name = "user_id")
	@GeneratedValue
	private Long userId;
	
	private String username;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "account_fk", referencedColumnName = "username")
	private Account account;

	private String name;
	
	private String surname;
	
	private Date bornDate;
	
	private String sex;
	
	private String email;
	
	private String mobileNumber;
	
	private String skype;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	@JoinColumn(name="photo_fk", referencedColumnName = "photo_id", nullable = true)
	private Photo profilePhoto;	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	private long loginExpireTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public long getLoginExpireTime() {
		return loginExpireTime;
	}

	public void setLoginExpireTime(long loginExpireTime) {
		this.loginExpireTime = loginExpireTime;
	}

}
