package com.achui.quick.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.achui.quick.domain.custom.JSONResponse;
import com.achui.quick.exception.DatabaseException;
import com.achui.quick.exception.MessageConstants;
import com.achui.quick.util.JSONResponposeBuilder;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException>{

	@Override
	public Response toResponse(DatabaseException ex) {
		StringBuffer sb = new StringBuffer(MessageConstants.ERROR_MSG_DATABASE);
		sb.append(" ").append(ex.getMessage());
		JSONResponse jsonResponse = JSONResponposeBuilder.buildJSONResponseStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode(), sb.toString(), true);
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(jsonResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
