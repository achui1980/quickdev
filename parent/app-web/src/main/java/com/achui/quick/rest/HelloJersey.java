package com.achui.quick.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.Json;
import com.achui.quick.domain.SysUser;
import com.achui.quick.query.Query;
import com.achui.quick.query.QueryHelper;
import com.achui.quick.service.MyUserService;
import com.achui.quick.spring.BasicSpringContext;
import com.achui.quick.spring.ISpringContext;
import com.achui.quick.spring.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("hello")
public class HelloJersey {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Context
	UriInfo uriInfo;
	@Context
	private HttpServletRequest servletRequest;
	@Autowired
	private MyUserService userService;
	
	@GET
	@Produces("text/plain")
	public String hello(){
		log.info("hello");
		BaseService service  = ServiceHelper.getBaseService("myuserService");
		return "Hello World,achui,"+userService+"	baseservice:"+service.findAll().size();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(){
		System.out.println("---------------"+servletRequest.getParameter("grant_type"));
		SysUser user = new SysUser();
		user.setUsername("test");
		return Response.created(uriInfo.getAbsolutePath())
				.entity(user).type(MediaType.APPLICATION_JSON)
				.build();
		
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
	@Path("/getuser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUsers(Query query){
		Map<String, Object> params = QueryHelper.buildQueryParams(query);
		PageRequest page = new PageRequest(query.getPage(),query.getPageCount());
		List<SysUser> userList = //userService.findAll();
				userService.findAll(QueryHelper.buildHQL(query), page, params);
		Long count = userService.count(QueryHelper.buildCountHQL(query), params);
		Json json = new Json();
		json.setData(userList);
		json.setTotal_count(count.intValue());
		json.setPos(page.getOffset());
		return Response.created(uriInfo.getAbsolutePath())
				.entity(json).type(MediaType.APPLICATION_JSON)
				.build();
		//return user;
		
	}
	
	@POST
	@Path("/user/op")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserPost(Map obj){
		ISpringContext context = BasicSpringContext.getSpringContext();
		BaseService service  = context.lookup("myuserService");
		//obj.setId(null);
		//obj.setPassword(RandomStringUtils.randomNumeric(6));
		//service.save(tttObject);
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
	
	@DELETE
	@Path("/user/op/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserDelete(@PathParam("id") Integer id){
		BaseService service = ServiceHelper.getBaseService("myuserService");
		service.delete(id);
		return Response.created(uriInfo.getAbsolutePath())
				.entity(id).type(MediaType.APPLICATION_JSON)
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
