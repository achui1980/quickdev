package com.achui.quick.rest;

import java.io.Serializable;


public class BaseUser<ID extends Serializable>{
	
	
	private ID id;

//	@XmlElement(type=Object.class)
//    @XmlSchemaType(name="anySimpleType")
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
