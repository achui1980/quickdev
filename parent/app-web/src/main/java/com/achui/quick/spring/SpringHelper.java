package com.achui.quick.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringHelper implements ApplicationContextAware{

	public static SpringHelper springHelper = new SpringHelper();
	    
    private static ApplicationContext applicationContext;
    
    public final static SpringHelper getInstance() {
        return springHelper;
    }
    
    private SpringHelper() {
        super();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
