package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name="sys_user")
public class SysUser extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=50)
	private String password;

	@Column(nullable=false, length=20)
	private String username;

	//bi-directional many-to-one association to SysUserRole
	@OneToMany(mappedBy="sysUser")
	private List<SysUserRole> sysUserRoles;

	public SysUser() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<SysUserRole> getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(List<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}