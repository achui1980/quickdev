package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
}