package com.achui.quick.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.achui.quick.rest.filter.CORSFilter;

public class Application extends ResourceConfig{
	public Application() {
		packages("com.achui.quick.rest","com.achui.quick.exception");
		register(CORSFilter.class);
		//register(LoggingResponseFilter.class);
		//register(CORSResponseFilter.class);
		//register features
		//register(LoggingFilter.class);
		register(JacksonFeature.class);	
		System.out.println("-----------------------start jersey");
	}
	public static void main(String[] args){
		System.out.println("dddd");
	}
}
