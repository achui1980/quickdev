package com.achui.quick.common.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private ID id;
	
	@Override
	public ID getId() {
		return id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;
	}

}
