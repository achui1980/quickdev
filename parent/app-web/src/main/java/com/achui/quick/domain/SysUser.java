package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.achui.quick.annotation.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name="sys_user")
public class SysUser extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Searchable(searchable=false)
	@Column(nullable=true, length=50)
	@JsonIgnore
	private String password;
	
	@Column(nullable=false, length=20)
	@Searchable(searchable=true,op="like")
	private String username;

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
}