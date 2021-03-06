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
@Table(name="BOOKINGS")
public class Booking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -107183963252039812L;
	
	@Id
	@Column(name = "booking_id")
	@GeneratedValue
	private Long bookingId;
	
	@ManyToOne
	@JoinColumn(name="tourist_fk", referencedColumnName="tourist_id")
	private Tourist tourist;
	
	@ManyToOne
	@JoinColumn(name="guide_fk", referencedColumnName= "guide_id")
	private Guide guide;
	
	private Date date;
	
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Tourist getTourist() {
		return tourist;
	}
	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}
	public Guide getGuide() {
		return guide;
	}
	public void setGuide(Guide guide) {
		this.guide = guide;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
