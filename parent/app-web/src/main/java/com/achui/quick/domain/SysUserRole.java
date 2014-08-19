package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the sys_user_role database table.
 * 
 */
@Entity
@Table(name="sys_user_role")
public class SysUserRole extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", nullable=false)
	private Integer roleId;

	@Column(name="user_id", nullable=false)
	private Integer userId;

	public SysUserRole() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}