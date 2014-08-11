package com.achui.quick.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.achui.quick.common.entity.BaseEntity;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	private String password;

	private String username;

	public User() {
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