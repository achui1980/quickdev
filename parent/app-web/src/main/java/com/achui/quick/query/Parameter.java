package com.achui.quick.query;

import org.apache.commons.lang3.StringUtils;


public class Parameter {

	private String name;
	
	private String value;
	
	private String op;

	public String getName() {
		return name;
	}
	
	// start_attr -> attr
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
