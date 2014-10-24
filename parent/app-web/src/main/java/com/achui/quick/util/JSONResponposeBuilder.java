package com.achui.quick.util;

import javax.ws.rs.core.Response.Status;

import com.achui.quick.domain.custom.JSONResponse;

public class JSONResponposeBuilder {

	public static JSONResponse buildJSONResponseStatus(int status, String errorMessage, boolean hasErrors) {
		JSONResponse response = new JSONResponse();
		response.setStatus(status);
		response.setStatusMessage(Status.fromStatusCode(status).getReasonPhrase());
		response.setErrorMessage(errorMessage);
		response.setHasErrors(hasErrors);
		return response;
	}
}
