package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_role database table.
 * 
 */
@Entity
@Table(name="sys_role")
public class SysRole extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=100)
	private String desc;

	@Column(nullable=false, length=20)
	private String name;

	@Column(nullable=false, length=20)
	private String role;

	//bi-directional many-to-one association to SysRoleResourcePermission
	@OneToMany(mappedBy="sysRole")
	private List<SysRoleResourcePermission> sysRoleResourcePermissions;

	//bi-directional many-to-one association to SysUserRole
	@OneToMany(mappedBy="sysRole")
	private List<SysUserRole> sysUserRoles;

	public SysRole() {
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<SysRoleResourcePermission> getSysRoleResourcePermissions() {
		return this.sysRoleResourcePermissions;
	}

	public void setSysRoleResourcePermissions(List<SysRoleResourcePermission> sysRoleResourcePermissions) {
		this.sysRoleResourcePermissions = sysRoleResourcePermissions;
	}

	public List<SysUserRole> getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(List<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}