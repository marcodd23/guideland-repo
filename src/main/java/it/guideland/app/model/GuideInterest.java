package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "GUIDES_INTERESTS")
//@IdClass(GuideInterestId.class)
@AssociationOverrides({
@AssociationOverride(name ="pk.guide", joinColumns = @JoinColumn(name ="guide_id")),
@AssociationOverride(name ="pk.interest", joinColumns = @JoinColumn(name ="interest_id"))
        })
public class GuideInterest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9041296287994600435L;

	private GuideInterestId pk = new GuideInterestId();
	
	
	
/*	@Id
	@Column(name = "guide_interest_id")
	@GeneratedValue
	public Long GuideInterestId;*/
	
	/*@Id
	private Long guideId;
	@Id
	private Long interestId;*/
	
	
	
/*	@ManyToOne(targetEntity = Interest.class)
	@JoinColumn(name="interest_fk", referencedColumnName="interest_id")
	public Interest interest;
	
	@ManyToOne(targetEntity = Guide.class)
	@JoinColumn(name="guide_fk", referencedColumnName="guide_id")
	public Guide guide;*/

	public int score;

/*	public Long getGuideInterestId() {
		return GuideInterestId;
	}

	public void setGuideInterestId(Long guideInterestId) {
		GuideInterestId = guideInterestId;
	}*/

/*	public Interest getInterest() {
		return interest;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}*/


	public int getScore() {
		return score;
	}

	@EmbeddedId
	public GuideInterestId getPk() {
		return pk;
	}

	public void setPk(GuideInterestId pk) {
		this.pk = pk;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Transient
	public Guide getGuide() {
		return pk.getGuide();
	}

	
	public void setGuide(Guide guide) {
		this.pk.setGuide(guide);
	}

	@Transient
	public Interest getInterest() {
		return pk.getInterest();
	}

	public void setInterest(Interest interest) {
		this.pk.setInterest(interest);
	}

}
