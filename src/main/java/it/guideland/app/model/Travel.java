package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class Travel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149632567503079501L;
	@Id
	@Column(name = "ITINERARY_ID")
	@GeneratedValue
	private Long travelId;
	@OneToOne
	@JoinColumn(name = "guide_fk")
	private Guide guide;
	@OneToOne
	@JoinColumn(name = "tourist_fk")
	private Tourist tourist;
	private Date date;
	private int duration;
	private String itinerary;
	public Long getTravelId() {
		return travelId;
	}
	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}
	public Guide getGuide() {
		return guide;
	}
	public void setGuide(Guide guide) {
		this.guide = guide;
	}
	public Tourist getTourist() {
		return tourist;
	}
	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getItinerary() {
		return itinerary;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
}
