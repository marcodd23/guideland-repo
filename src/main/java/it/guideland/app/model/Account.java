package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4700342434392039227L;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Id
	@Column(name = "account_id")
	@GeneratedValue
	private Long accountId;

	/*	@OneToOne(mappedBy = "account")
		private User user;*/

	@Column(unique = true)
	private String usernameEmail;

	@JsonIgnore
	private String password;

	private Date lastLogin;

	private Date registrationDate;

	private boolean enabled = true;

	@Column(name = "not_locked")
	private boolean notLocked = true;

	@Column(name = "not_expired")
	private boolean notExpired = true;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUsernameEmail() {
		return usernameEmail;
	}

	public void setUsernameEmail(String usernameEmail) {
		this.usernameEmail = usernameEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isNotLocked() {
		return notLocked;
	}

	public void setNotLocked(boolean notLocked) {
		this.notLocked = notLocked;
	}

	public boolean isNotExpired() {
		return notExpired;
	}

	public void setNotExpired(boolean notExpired) {
		this.notExpired = notExpired;
	}

	public static class AccountBuilder {
		private String usernameEmail;
		private String password;
		private Date registrationDate;
		private boolean enabled;
		private boolean notLocked;
		private boolean notExpired;

		public AccountBuilder usernameEmail(String usernameEmail) {
			this.usernameEmail = usernameEmail;
			return this;
		}

		public AccountBuilder password(String password) {
			this.password = password;
			return this;
		}

		public AccountBuilder registrationDate(Date registrationDate) {
			this.registrationDate = registrationDate;
			return this;
		}

		public AccountBuilder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public AccountBuilder notLocked(boolean notLocked) {
			this.notLocked = notLocked;
			return this;
		}

		public AccountBuilder notExpired(boolean notExpired) {
			this.notExpired = notExpired;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}

	private Account(AccountBuilder builder) {
		this.usernameEmail = builder.usernameEmail;
		setPassword(builder.password); 
		this.registrationDate = builder.registrationDate;
		this.enabled = builder.enabled;
		this.notLocked = builder.notLocked;
		this.notExpired = builder.notExpired;
	}
}
