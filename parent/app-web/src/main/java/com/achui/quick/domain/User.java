package com.achui.quick.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.achui.quick.common.entity.BaseEntity;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User extends BaseEntity<Integer>  {

	private String password;

	private String username;

	public User() {
	}

    public User(Integer id) {
        setId(id);
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