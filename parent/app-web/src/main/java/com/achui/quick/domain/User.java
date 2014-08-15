package com.achui.quick.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User extends com.achui.quick.common.entity.BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private int id;
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