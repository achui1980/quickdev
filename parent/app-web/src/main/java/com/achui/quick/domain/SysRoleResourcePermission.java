package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_role_resource_permission database table.
 * 
 */
@Entity
@Table(name="sys_role_resource_permission")
public class SysRoleResourcePermission extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="permission_ids", length=200)
	private String permissionIds;

	//bi-directional many-to-one association to SysResource
	@ManyToOne
	@JoinColumn(name="resource_id")
	private SysResource sysResource;

	//bi-directional many-to-one association to SysRole
	@ManyToOne
	@JoinColumn(name="role_id")
	private SysRole sysRole;

	public SysRoleResourcePermission() {
	}

	public String getPermissionIds() {
		return this.permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}