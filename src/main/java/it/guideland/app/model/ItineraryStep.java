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
@Table(name="ITINERARY_STEPS")
public class ItineraryStep implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 147705720641863994L;

    @Id
    @GeneratedValue
    @Column(name="itinerarystep_id")
    private Long itineraryStepId;

    private String step;
    
    private String stepDescription;

    private Date startTime;
    
    @ManyToOne(targetEntity = Itinerary.class)
    @JoinColumn(name="itinerary_fk", referencedColumnName="itinerary_id")
    private Itinerary itinerary;

	public Long getItineraryStepId() {
		return itineraryStepId;
	}

	public void setItineraryStepId(Long itineraryStepId) {
		this.itineraryStepId = itineraryStepId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public String getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}
	
}
