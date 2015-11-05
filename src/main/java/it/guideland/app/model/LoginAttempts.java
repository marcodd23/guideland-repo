package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

public class LoginAttempts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3309529665172681652L;
	
	@Id
	private String email;
	private int counter;
	private Date date;

}
