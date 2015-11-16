package it.guideland.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRAVELS")
public class Travel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149632567503079501L;
	

    @Id
    @GeneratedValue
    @Column(name="travel_id")
    private Long travelId;
	
    private Date date;

    private int duration;

    @OneToOne(targetEntity = Tourist.class)
    @JoinColumn(name="tourist_fk", referencedColumnName="tourist_id")
    private Tourist tourist;
    
    @OneToOne(targetEntity = Guide.class)
    @JoinColumn(name="guide_fk", referencedColumnName="guide_id")
    private Guide guide;

    @OneToOne(targetEntity = Booking.class)
    @JoinColumn(name="booking_fk", referencedColumnName="booking_id")
    private Booking booking;

    @OneToOne(targetEntity = Itinerary.class)
    @JoinColumn(name="itinerary_fk", referencedColumnName="itinerary_id")
    private Itinerary itinerary;

    @OneToMany(targetEntity = Interest.class)
    private List<Interest> interests = new ArrayList<Interest>();

	public Long getTravelId() {
		return travelId;
	}

	public void setTravelId(Long travelId) {
		this.travelId = travelId;
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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
   
}
