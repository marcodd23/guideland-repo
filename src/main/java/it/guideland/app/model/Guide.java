package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Guide implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3796592601834095519L;
	
	@Id
	@Column(name = "GUIDE_ID")
	@GeneratedValue
	private Long guideId;
	@OneToOne
	@JoinColumn(name = "user_fk")
	private User user;
	@ManyToOne
	@JoinColumn(name="city_fk")
	private City city;
	private String description;
	private String hourlyrate;
	private int score;
	private Date registrationDate;
	
	public Long getGuideId() {
		return guideId;
	}
	public void setGuideId(Long guideId) {
		this.guideId = guideId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHourlyrate() {
		return hourlyrate;
	}
	public void setHourlyrate(String hourlyrate) {
		this.hourlyrate = hourlyrate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
