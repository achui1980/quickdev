package com.achui.quick.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;


public class BaseUser<ID extends Serializable>{
	
	
	private ID id;

//	@XmlElement(type=Object.class)
//    @XmlSchemaType(name="anySimpleType")
	@XmlAnyElement(lax=true)
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
