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
@Table(name="REQUESTS")
public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680788811755497825L;
	

    @Id
    @GeneratedValue
    @Column(name="request_id")
    private Long requestId;
	
    private String requestText;

    @ManyToOne(targetEntity = Tourist.class)
    @JoinColumn(name="tourist_fk", referencedColumnName="tourist_id")
    private Tourist tourist;

    private boolean answer;

    private Date dateStart;

    private String dateEnd;

    private boolean availability;

    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name="guide_fk", referencedColumnName="guide_id")
    private Guide guide;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public Tourist getTourist() {
		return tourist;
	}

	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}
}
