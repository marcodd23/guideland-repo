package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SPEC_INTERESTS", uniqueConstraints=@UniqueConstraint(columnNames = {"city", "interestDescription"}))
public class Interest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7595573616384064857L;
	
	@Id
	@Column(name = "INTEREST_ID")
	@GeneratedValue
	private Long interest_id;
	
	@ManyToOne
	private Guide guide;
	private int score;

	
}
