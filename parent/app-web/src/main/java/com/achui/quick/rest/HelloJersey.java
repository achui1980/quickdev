package com.achui.quick.rest;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.domain.SysUser;
import com.achui.quick.service.MyUserService;

@Path("hello")
public class HelloJersey {
	@Context
	UriInfo uriInfo;
	@Autowired
	private MyUserService authService;
	
	@GET
	@Produces("text/plain")
	public String hello(){
		return "Hello World,achui,"+authService;
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(){
		User user = new User();
		//user.setId(100);
		user.setPassword("123");
		user.setUsername("achui");
//		return Response.created(uriInfo.getAbsolutePath())
//				.entity(user).type(MediaType.APPLICATION_XML)
//				.build();
		return user;
		
	}
	
	 public static void main(String[] args) throws Exception {
	        User user = new User();
	        user.setId(100);
	        user.setUsername("achui");
	        user.setPassword("20");
	 
	        JAXBContext jc = JAXBContext.newInstance(User.class);
	 
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(user, System.out);
	    }
}
