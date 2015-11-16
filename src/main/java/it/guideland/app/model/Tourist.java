package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TOURISTS")
public class Tourist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1709359420911257878L;
	
	@Id
	@Column(name = "tourist_id")
	@GeneratedValue
	private Long touristId;
	
	@OneToOne
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	private User user;
	
	private int score;

	public Long getTouristId() {
		return touristId;
	}

	public void setTouristId(Long touristId) {
		this.touristId = touristId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
