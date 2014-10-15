package com.achui.quick.spring;

import com.achui.quick.common.service.BaseService;

public class ServiceHelper {

	public static BaseService getBaseService(String name){
		ISpringContext context = BasicSpringContext.getSpringContext();
		BaseService service  = context.lookup(name);
		return service;
	}
}
