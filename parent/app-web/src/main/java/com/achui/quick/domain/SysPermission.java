package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_permission database table.
 * 
 */
@Entity
@Table(name="sys_permission")
public class SysPermission extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=50)
	private String desc;

	@Column(nullable=false, length=50)
	private String name;

	@Column(length=20)
	private String op;

	@Column(length=10)
	private String type;

	@Column(name="sys_resource_id", nullable=false)
	private Integer sysResourceId;

	public SysPermission() {
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

	public String getOp() {
		return this.op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getSysResourceId() {
		return sysResourceId;
	}


	public void setSysResourceId(Integer sysResourceId) {
		this.sysResourceId = sysResourceId;
	}
}