package com.achui.quick.rest.provider;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

	 private ObjectMapper mapper = null;
	 public ObjectMapperContextResolver() {
	        super();

	        // Illustrate configuration of the mapper instance
	        mapper = new ObjectMapper()
	        		.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
	        		.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	   
	    }
	@Override
	public ObjectMapper getContext(Class<?> type) {
		// TODO Auto-generated method stub
		return mapper;
	}

}
