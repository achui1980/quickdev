package com.achui.quick.spring;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class CustomMappingJacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter{
	
	public CustomMappingJacksonHttpMessageConverter(){
		super();
	}

	@Override
	public void setObjectMapper(ObjectMapper objectMapper) {
		objectMapper = objectMapper.configure(Feature.WRAP_ROOT_VALUE, true)
					.configure(Feature.WRAP_ROOT_VALUE, true);
		super.setObjectMapper(objectMapper);
	}
	
	
	
}
