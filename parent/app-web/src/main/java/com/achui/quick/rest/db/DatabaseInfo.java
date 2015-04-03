package com.achui.quick.rest.db;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.constants.ServiceConstants;
import com.achui.quick.db.ColumnMeta;
import com.achui.quick.db.DBUtil;
import com.achui.quick.domain.custom.JSONResponse;
import com.achui.quick.domain.custom.JSONResponseStatus;
import com.achui.quick.util.JSONResponposeBuilder;
import com.google.common.collect.Maps;

@Path("service")
public class DatabaseInfo {
	
	private Logger logger = LoggerFactory.getLogger(DatabaseInfo.class);
	
	@Autowired
	DataSource dataSource;
	
	@GET
	@Path("/dbinfo/{domain}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDbInfo(@PathParam("domain") String domain){
		JSONResponse response = JSONResponposeBuilder.buildJSONResponseStatus(JSONResponseStatus.Status.OK.getStatusCode(), null, false);
		Map<String, Object> dataInfo = Maps.newHashMap();
		Class clzz;
		try {
			clzz = Class.forName(ServiceConstants.SERVICE_DOMAIN_PACKAGE+"."+domain);
			Map<String, ColumnMeta> metaInfo = DBUtil.getMetaInfo(dataSource,clzz);
			dataInfo.put("columnInfoMap", metaInfo);
			dataInfo.put("domain", domain);
			String service = StringUtils.uncapitalize(domain.replace(ServiceConstants.DOMAIN_PREFIX, "") + "Service");
			dataInfo.put("serviceName",service);
			response.setData(dataInfo);
		} catch (ClassNotFoundException e) {
			response.setStatus(JSONResponseStatus.Status.IMAGE_ERROR_EXTENSION.getStatusCode());
			response.setHasErrors(true);
			response.setErrorMessage(e.getMessage());
			logger.error("Class not found",e);
			return Response.serverError().entity(response).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok().entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
