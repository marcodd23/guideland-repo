package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FeedbackTourist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8707265861917955587L;
	
	@Id
	@Column(name = "FEEDBACK_TOURIST_ID")
	@GeneratedValue
	private Long feedbackTouristId;
	
	@ManyToOne
	@JoinColumn(name="guide_fk")
	private Guide guide;
	
	@ManyToOne
	@JoinColumn(name="tourist_fk")
	private Tourist tourist;
	private Date date;
	private int score;
	private String review;
	public Long getFeedbackTouristId() {
		return feedbackTouristId;
	}
	public void setFeedbackTouristId(Long feedbackTouristId) {
		this.feedbackTouristId = feedbackTouristId;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	
	
	
}
