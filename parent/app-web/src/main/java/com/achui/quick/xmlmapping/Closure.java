package com.achui.quick.xmlmapping;

public interface Closure<T> {

	public void execute(T input);
	
	public String toXquery();
}
