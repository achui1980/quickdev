package com.achui.quick.spring;

import org.springframework.context.ApplicationContext;

public interface ISpringContext {
	
	ApplicationContext getContext();
    
    <T> T lookup(String type);
    
    <T> T lookup(Class cls);
    
    boolean isSingleton(Class cls);
}
