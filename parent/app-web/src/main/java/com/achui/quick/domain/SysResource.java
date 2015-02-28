package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.achui.quick.annotation.Display;
import com.achui.quick.annotation.Searchable;

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
	@Searchable(searchable=false)
	private String icon;

	@Column(name="is_show", length=1)
	@Searchable(searchable=false)
	private String isShow;

	@Column(length=30)
	private String module;

	@Searchable(searchable=false)
	@Display
	@Column(name="display_order")
	private Integer displayOrder;

	@Column(name="parent_ids", length=50)
	@Searchable(searchable=false)
	private String parentIds;

	@Column(length=100)
	private String url;

	@Column(name="parent_id")
	@Searchable(searchable=false)
	private Integer parentId;

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

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}