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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.guideland.app.utils.CustomDateSerializer;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4914191730785149204L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue
	private Long userId;

	private String usernameEmail;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "account_fk", referencedColumnName = "account_id")
	private Account account;
	
	private String alternativeEmail;

	private String name;

	private String surname;

	@JsonSerialize(using=CustomDateSerializer.class)
	private Date bornDate;

	private String sex;

	private String mobileNumber;

	private String skype;

	@Transient
	@JsonIgnore
	private String currentPassword;

	@Transient
	@JsonIgnore
	private String newPassword;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "photo_fk", referencedColumnName = "photo_id", nullable = true)
	private Photo profilePhoto;

	/*@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_id") )
	private List<Role> roles = new ArrayList<>();*/
	
	@OneToOne(targetEntity=Role.class)
	@JoinColumn(name = "role_fk", referencedColumnName = "role_id", nullable = true)
	private Role role;

	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public String getUsernameEmail() {
		return usernameEmail;
	}




	public void setUsernameEmail(String usernameEmail) {
		this.usernameEmail = usernameEmail;
	}




	public Account getAccount() {
		return account;
	}




	public void setAccount(Account account) {
		this.account = account;
	}




	public String getAlternativeEmail() {
		return alternativeEmail;
	}




	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
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




	public String getCurrentPassword() {
		return currentPassword;
	}




	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}




	public String getNewPassword() {
		return newPassword;
	}




	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}




	public Photo getProfilePhoto() {
		return profilePhoto;
	}




	public void setProfilePhoto(Photo profilePhoto) {
		this.profilePhoto = profilePhoto;
	}




	public Role getRole() {
		return role;
	}




	public void setRole(Role role) {
		this.role = role;
	}


/*	public void grantRole(Role role) {
		if (role != null) {
			roles.add(role);
		}
	}

	public void revokeRole(Role role) {
		if (roles != null) {
			roles.remove(role);
		}
	}*/


	public static class UserBuilder {
		private String usernameEmail;
		private Account account;
		private String name;
		private String surname;
		private Date bornDate;
		private String sex;
		private String mobileNumber;
		private String skype;
		//private List<Role> roles;
		private Role role;

		public UserBuilder usernameEmail(String usernameEmail) {
			this.usernameEmail = usernameEmail;
			return this;
		}

		public UserBuilder account(Account account) {
			this.account = account;
			return this;
		}

		public UserBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UserBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}

		public UserBuilder bornDate(Date bornDate) {
			this.bornDate = bornDate;
			return this;
		}

		public UserBuilder sex(String sex) {
			this.sex = sex;
			return this;
		}

		public UserBuilder mobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public UserBuilder skype(String skype) {
			this.skype = skype;
			return this;
		}

		public UserBuilder role(Role role) {
			this.role = role;
			return this;
		}

		public User build() {
			User user = new User();
			user.setUsernameEmail(usernameEmail);
			user.setAccount(account);
			user.setName(name);
			user.setSurname(surname);
			user.setBornDate(bornDate);
			user.setSex(sex);
			user.setMobileNumber(mobileNumber);
			user.setSkype(skype);
			user.setRole(role);
			return user;
		}
	}
}
