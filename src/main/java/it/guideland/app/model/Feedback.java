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
@Table(name="FEEDBACKS")
public class Feedback implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5138960409227777948L;


    @Id
    @GeneratedValue
    @Column(name="feedback_id")
    private Long feedbackId;
	
    private Date date;

    private String score;

    private int answerTime;

    private String serviceQuality;

    private String review;

    private FeedbackType feedbackType;
    

    @ManyToOne(targetEntity = Tourist.class)
    @JoinColumn(name="tourist_fk", referencedColumnName="tourist_id")
    private Tourist tourist;

    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name="guide_fk", referencedColumnName="guide_id")
    private Guide guide;
    
	public enum FeedbackType {
		GUIDE(1), 
		TOURIST(2);
		
		private int type;

		private FeedbackType(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(int answerTime) {
		this.answerTime = answerTime;
	}

	public String getServiceQuality() {
		return serviceQuality;
	}

	public void setServiceQuality(String serviceQuality) {
		this.serviceQuality = serviceQuality;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
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
}
