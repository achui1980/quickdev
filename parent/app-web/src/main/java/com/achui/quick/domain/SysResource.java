package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_resource database table.
 * 
 */
@Entity
@Table(name="sys_resource")
public class SysResource extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(length=50)
	private String icon;

	@Column(name="is_show", length=1)
	private String isShow;

	@Column(length=30)
	private String module;

	private int order;

	@Column(name="parent_ids", length=50)
	private String parentIds;

	@Column(length=100)
	private String url;

	//bi-directional many-to-one association to SysPermission
	@OneToMany(mappedBy="sysResource")
	private List<SysPermission> sysPermissions;

	//bi-directional many-to-one association to SysResource
	@ManyToOne
	@JoinColumn(name="parent_id")
	private SysResource sysResource;

	//bi-directional many-to-one association to SysResource
	@OneToMany(mappedBy="sysResource")
	private List<SysResource> sysResources;

	//bi-directional many-to-one association to SysRoleResourcePermission
	@OneToMany(mappedBy="sysResource")
	private List<SysRoleResourcePermission> sysRoleResourcePermissions;

	public SysResource() {
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsShow() {
		return this.isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getParentIds() {
		return this.parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysPermission> getSysPermissions() {
		return this.sysPermissions;
	}

	public void setSysPermissions(List<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	public List<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(List<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

	public List<SysRoleResourcePermission> getSysRoleResourcePermissions() {
		return this.sysRoleResourcePermissions;
	}

	public void setSysRoleResourcePermissions(List<SysRoleResourcePermission> sysRoleResourcePermissions) {
		this.sysRoleResourcePermissions = sysRoleResourcePermissions;
	}

}