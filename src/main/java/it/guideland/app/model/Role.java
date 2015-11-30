package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6460636091496435177L;

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	@JsonIgnore
	private Long roleId;
	
/*	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "roles")
	private List<User> users = new ArrayList<>();*/
	
	private String role;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public enum RoleType {
		USER("ROLE_USER"), 
		ADMIN("ROLE_ADMIN");
		
		private String type;

		private RoleType(String type) {
			this.type = type;
		}
		
		@Override
		public String toString() {
			return type;
		}	
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
