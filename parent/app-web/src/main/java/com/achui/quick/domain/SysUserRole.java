package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_user_role database table.
 * 
 */
@Entity
@Table(name="sys_user_role")
public class SysUserRole extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to SysRole
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	private SysRole sysRole;

	//bi-directional many-to-one association to SysUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private SysUser sysUser;

	public SysUserRole() {
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}