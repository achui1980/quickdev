package com.achui.quick.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class Application extends ResourceConfig{
	public Application() {
		super(
				JacksonFeature.class
		);
		System.out.println("-----------------------start jersey");
	}
}
