package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class GuideInterestId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729465710231923872L;

	private Guide guide;
	private Interest interest;
	
	@ManyToOne
	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	@ManyToOne
	public Interest getInterest() {
		return interest;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}

	@Override
	public int hashCode() {
		int result;
		result = (guide != null ? guide.hashCode() : 0);
		result = 31 * result + (interest != null ? interest.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		GuideInterestId that = (GuideInterestId) obj;

		if (guide != null ? !guide.equals(that.guide) : that.guide != null) {
			return false;
		}
		if (interest != null ? !interest.equals(that.interest) : that.interest != null) {
			return false;
		}
		return true;
	}

}
