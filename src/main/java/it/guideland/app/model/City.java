package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITIES")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5790805178723293605L;
	
	@Id
	@Column(name = "city_id")
	@GeneratedValue
	private Long cityId;
	
	@Column(unique=true)
	private String name;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public City(String name) {
		super();
		this.name = name;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
