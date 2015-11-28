package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4700342434392039227L;

	@Id
	@Column(name = "account_id")
	@GeneratedValue
	private Long accountId;
	
	@Column(unique = true)
	private String username;

	/*	@OneToOne(mappedBy = "account")
		private User user;*/
	
	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String password;

	private Date lastLogin;

	private Date registrationDate;

	private boolean enabled;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public static class AccountBuilder {
		private String username;
		private String email;
		private String password;
		private Date registrationDate;
		private boolean enabled;

		public AccountBuilder username(String username) {
			this.username = username;
			return this;
		}

		public AccountBuilder email(String email) {
			this.email = email;
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

		public Account build() {
			return new Account(this);
		}
	}

	private Account(AccountBuilder builder) {
		this.username = builder.username;
		this.email = builder.email;
		this.password = builder.password;
		this.registrationDate = builder.registrationDate;
		this.enabled = builder.enabled;
	}
}
