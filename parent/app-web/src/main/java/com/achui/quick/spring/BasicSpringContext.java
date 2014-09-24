package com.achui.quick.spring;

import org.apache.commons.lang3.StringUtils;

public class BasicSpringContext extends SpringContext{

	static BasicSpringContext springContext = new BasicSpringContext();

	public static BasicSpringContext getSpringContext() {
        return springContext;
    }
	
	public static void setSpringContext(BasicSpringContext springContext) {
        BasicSpringContext.springContext = springContext;
    }
	
	public <T> T lookup(String type) {
        if(getContext().containsBean(type)) {
            return (T) getContext().getBean(type);
        }
        return null;
    }

    public <T> T lookup(Class cls) {
        String type = cls.getSimpleName();
        return lookup(StringUtils.uncapitalize(type));
    }
    
    public boolean isSingleton(Class cls) {
        return getContext().isSingleton(StringUtils.uncapitalize(cls.getSimpleName()));
    }
}
