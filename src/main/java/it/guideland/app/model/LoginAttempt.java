package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN_ATTEMPTS")
public class LoginAttempt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3309529665172681652L;
	
	@Id
	@GeneratedValue
	@Column(name = "login_attempt_id")
	private Long loginAttemptId;

    private Date date;

    private String counter;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name="account_fk", referencedColumnName="username")
    private Account account;

	public Long getLoginAttemptId() {
		return loginAttemptId;
	}

	public void setLoginAttemptId(Long loginAttemptId) {
		this.loginAttemptId = loginAttemptId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
