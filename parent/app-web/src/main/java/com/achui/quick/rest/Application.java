package com.achui.quick.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig{
	public Application() {
		packages("com.achui.quick.rest","com.achui.quick.exception");
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
