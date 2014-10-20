package com.achui.quick.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.constants.ServiceConstants;
import com.achui.quick.domain.Json;
import com.achui.quick.query.Query;
import com.achui.quick.query.QueryHelper;
import com.achui.quick.spring.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("service")
public class BaseRestService {

	private Logger logger = LoggerFactory.getLogger(BaseRestService.class);
	
	@GET
	@Produces("text/plain")
	public String hello(){
		BaseService service  = ServiceHelper.getBaseService("myuserService");
		return "Hello World,achui,	baseservice:"+service.findAll().size();
	}
	
	@POST
	@Path("/{service}/op/{domain}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  Response add(@PathParam("service") String serviceName, @PathParam("domain") String domain,Object obj){
		Object convert = convert(domain, obj);
		BaseService service = ServiceHelper.getBaseService(serviceName);
		convert = service.save(convert);
		return Response.ok().entity(convert).type(MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/{service}/op/{domain}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(@PathParam("service") String serviceName, @PathParam("domain") String domain,Object obj){
		return add(serviceName,domain,obj);
	}
	
	@DELETE
	@Path("/{service}/op/{domain}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id,@PathParam("service") String serviceName){
		BaseService service = ServiceHelper.getBaseService(serviceName);
		service.delete(id);
		return Response.ok().entity(id).type(MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/{service}/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response list(@PathParam("service") String serviceName,Query query){
		BaseService service = ServiceHelper.getBaseService(serviceName);
		Map<String, Object> params = QueryHelper.buildQueryParams(query);
		PageRequest page = new PageRequest(query.getPage(),query.getPageCount());
		List userList = //userService.findAll();
				service.findAll(QueryHelper.buildHQL(query), page, params);
		Long count = service.count(QueryHelper.buildCountHQL(query), params);
		Json json = new Json();
		json.setData(userList);
		json.setTotal_count(count.intValue());
		json.setPos(page.getOffset());
		return Response.ok()
				.entity(json).type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	private Object convert(String domain,Object value){
		ObjectMapper mapper = new ObjectMapper();
		Object result = null;
		try {
			Class cls = Class.forName(ServiceConstants.SERVICE_DOMAIN_PACKAGE+"."+domain);
			result = mapper.convertValue(value, cls);
		} catch (ClassNotFoundException e) {
			logger.error("class not found", e);
		}
		return result;
	}
}
