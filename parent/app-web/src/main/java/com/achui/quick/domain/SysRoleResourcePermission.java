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

	@Column(name="resource_id")
	private Integer resourceId;

	@Column(name="role_id")
	private Integer roleId;

	public SysRoleResourcePermission() {
	}

	public String getPermissionIds() {
		return this.permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}