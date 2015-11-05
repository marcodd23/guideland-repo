package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SPEC_INTERESTS", uniqueConstraints=@UniqueConstraint(columnNames = {"city", "interestDescription"}))
public class specificInterest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4193641744262494744L;
	
	@Id
	@Column(name = "SPEC_INTEREST_ID")
	@GeneratedValue
	private Long interestId;
	@OneToOne
	@JoinColumn(name = "city_fk")
	private City city;
	private String interestDescription;
	private int counter;
	public Long getInterestId() {
		return interestId;
	}
	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getInterestDescription() {
		return interestDescription;
	}
	public void setInterestDescription(String interestDescription) {
		this.interestDescription = interestDescription;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
}
