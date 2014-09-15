package com.achui.quick.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;


public abstract class Base<ID extends Serializable> {
	
	public abstract ID getId();
	
	public abstract void setId(final ID id);
}
