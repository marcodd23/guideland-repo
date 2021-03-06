package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "INTERESTS", uniqueConstraints=@UniqueConstraint(columnNames = {"city_fk", "interestDescription"}))
public class Interest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7595573616384064857L;
	

		@Id
		@GeneratedValue
		@Column(name="interest_id")
		private Long interestId;	
		
	    @ManyToOne(targetEntity = City.class, optional=true)
	    @JoinColumn(name="city_fk", referencedColumnName="city_id", nullable=true)
	    private City city;

	    @ManyToOne(targetEntity = Guide.class, optional=true)
	    private Guide guide;
		
	    @Basic(optional=true)
	    @Column(nullable=true)
	    private int score;

	    private String interestType;

	    @ManyToOne(targetEntity = Interest.class, optional=true)
	    @JoinColumn(name="parent_interest_fk", referencedColumnName="interest_id", nullable=true)
	    private Interest parentInterest;

	    @Basic
	    private String interesName;
	    
	    @Basic
	    private int interestDescription;

	    @Basic(optional=true)
	    @Column(nullable=true)
	    private int counter;
	   
	    
		public enum InterestType {
			GUIDE(1), 
			TOURIST(2);
			
			private int type;

			private InterestType(int type) {
				this.type = type;
			}

			public int getType() {
				return type;
			}
		}


		public Long getInterestId() {
			return interestId;
		}


		public void setInterestId(Long interestId) {
			this.interestId = interestId;
		}


		public City getCity() {
			return city;
		}


		public void setCity(City city) {
			this.city = city;
		}


		public Guide getGuide() {
			return guide;
		}


		public void setGuide(Guide guide) {
			this.guide = guide;
		}


		public int getScore() {
			return score;
		}


		public void setScore(int score) {
			this.score = score;
		}


		public String getInterestType() {
			return interestType;
		}


		public void setInterestType(String interestType) {
			this.interestType = interestType;
		}


		public Interest getParentInterest() {
			return parentInterest;
		}


		public void setParentInterest(Interest parentInterest) {
			this.parentInterest = parentInterest;
		}


		public String getInteresName() {
			return interesName;
		}


		public void setInteresName(String interesName) {
			this.interesName = interesName;
		}


		public int getInterestDescription() {
			return interestDescription;
		}


		public void setInterestDescription(int interestDescription) {
			this.interestDescription = interestDescription;
		}


		public int getCounter() {
			return counter;
		}


		public void setCounter(int counter) {
			this.counter = counter;
		}

}
