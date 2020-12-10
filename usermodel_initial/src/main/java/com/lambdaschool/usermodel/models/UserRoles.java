package com.lambdaschool.usermodel.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "userroles")
@IdClass(UserRolesId.class)
public class UserRoles
		extends Auditable
		implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties(value = "roles",
	                      allowSetters = true)
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "roleid")
	@JsonIgnoreProperties(value = "users",
	                      allowSetters = true)
	private Role role;

	public UserRoles() {}

	public UserRoles(
			User user,
			Role role
	) {
		this.user = user;
		this.role = role;
	}

	/* skip this function, only included for valid implementation of Serializable */
	@Override
	public int hashCode() {
		return 23;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof UserRoles)) {
			return false;
		}

		UserRoles that    = (UserRoles) o;
		long      thisUid = this.user == null ? 0 : this.user.getUserid();
		long thatUid = that.user == null
		               ? 0
		               : that.getUser()
		                     .getUserid();
		long thisRid = this.role == null ? 0 : this.role.getRoleid();
		long thatRid = that.role == null
		               ? 0
		               : that.getRole()
		                     .getRoleid();
		return (thisUid == thatUid) && (thisRid == thatRid);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
