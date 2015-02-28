package com.achui.quick.xmlmapping;

public interface Condition<T> {
	
	public boolean evaluate(T object);
	
	public String toXquery();
}
