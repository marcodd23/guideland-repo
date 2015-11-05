package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FeedbackGuide implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FEEDBACK_GUIDE_ID")
	@GeneratedValue
	private Long feedbackGuideId;
	
	@ManyToOne
	@JoinColumn(name="guide_fk")
	private Guide guide;
	
	@ManyToOne
	@JoinColumn(name="tourist_fk")
	private Tourist tourist;
	private Date date;
	private int serviceQuality;
	private int timeToAnswer;
	private String review;
	public Long getFeedbackGuideId() {
		return feedbackGuideId;
	}
	public void setFeedbackGuideId(Long feedbackGuideId) {
		this.feedbackGuideId = feedbackGuideId;
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
	public int getServiceQuality() {
		return serviceQuality;
	}
	public void setServiceQuality(int serviceQuality) {
		this.serviceQuality = serviceQuality;
	}
	public int getTimeToAnswer() {
		return timeToAnswer;
	}
	public void setTimeToAnswer(int timeToAnswer) {
		this.timeToAnswer = timeToAnswer;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	
	
}
