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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "GUIDES")
public class Guide implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3796592601834095519L;
	
	@Id
	@Column(name = "guide_id")
	@GeneratedValue
	private Long guideId;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "user_fk", referencedColumnName="user_id")
	private User user;
	
	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name="city_fk", referencedColumnName="city_id")
	private City city;
	
	@OneToMany(targetEntity=GuideInterest.class, fetch = FetchType.LAZY, mappedBy ="pk.guide", cascade = 
	    {CascadeType.PERSIST, CascadeType.MERGE})
	private List<GuideInterest> guideInterests = new ArrayList<>();
	
	private String description;
	
	private String hourlyRate;
	
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
	public String getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
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
	public List<GuideInterest> getGuideInterests() {
		return guideInterests;
	}
	public void setGuideInterests(List<GuideInterest> guideInterests) {
		this.guideInterests = guideInterests;
	}
	public void addGuideInterest(GuideInterest guideInterest){
		this.guideInterests.add(guideInterest);
	}

}
