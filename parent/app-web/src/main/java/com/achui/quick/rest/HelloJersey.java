package com.achui.quick.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.domain.SysUser;
import com.achui.quick.service.MyUserService;

@Path("hello")
public class HelloJersey {
	@Context
	UriInfo uriInfo;
	@Autowired
	private MyUserService userService;
	
	
	@GET
	@Produces("text/plain")
	public String hello(){
		return "Hello World,achui,"+userService;
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(Object obj){
		List<SysUser> userList = userService.findAll();
//		User user = new User();
//		//user.setId(100);
//		user.setPassword("123");
//		user.setUsername("achui");
		return Response.created(uriInfo.getAbsolutePath())
				.entity(userList).type(MediaType.APPLICATION_JSON)
				.build();
		//return user;
		
	}
	
	@POST
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserPost(SysUser obj){
		System.out.println(obj);
		List<SysUser> userList = userService.findAll();
//		User user = new User();
//		//user.setId(100);
//		user.setPassword("123");
//		user.setUsername("achui");
		return Response.created(uriInfo.getAbsolutePath())
				.entity(userList).type(MediaType.APPLICATION_JSON)
				.build();
		//return user;
		
	}
	
	@PUT
	@Path("/user/op/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserPut(@PathParam("id") Integer id,SysUser obj){
		System.out.println(obj.getUsername());
		System.out.println("id:"+id);
		
		List<SysUser> userList = userService.findAll();
//		User user = new User();
//		//user.setId(100);
//		user.setPassword("123");
//		user.setUsername("achui");
		return Response.created(uriInfo.getAbsolutePath())
				.entity(userList).type(MediaType.APPLICATION_JSON)
				.build();
		//return user;
		
	}
	
	 public static void main(String[] args) throws Exception {
//	        User user = new User();
//	        user.setId(100);
//	        user.setUsername("achui");
//	        user.setPassword("20");
//	 
//	        JAXBContext jc = JAXBContext.newInstance(User.class);
//	 
//	        Marshaller marshaller = jc.createMarshaller();
//	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//	        marshaller.marshal(user, System.out);
		 	com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			com.achui.quick.domain.SysUser user = new com.achui.quick.domain.SysUser();
			String aaString =  mapper.writeValueAsString(user);
			System.out.println(aaString);
	    }
}
