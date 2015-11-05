package it.guideland.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class FeedbackTravel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -909215937481677079L;
	
	@Id
	@Column(name = "FEEDBACKTRAVEL_ID")
	@GeneratedValue
	private Long feedbackTravelId;
	
	@OneToOne
	@JoinColumn(name = "travel_fk")
	private Travel travel;
	private Date date;
	private int score;
	private String review;
	public Long getFeedbackTravelId() {
		return feedbackTravelId;
	}
	public void setFeedbackTravelId(Long feedbackTravelId) {
		this.feedbackTravelId = feedbackTravelId;
	}
	public Travel getTravel() {
		return travel;
	}
	public void setTravel(Travel travel) {
		this.travel = travel;
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
