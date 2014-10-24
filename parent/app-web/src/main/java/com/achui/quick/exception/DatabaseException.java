package com.achui.quick.exception;

import javax.ws.rs.WebApplicationException;

public class DatabaseException extends WebApplicationException{

	public DatabaseException(String msg){
		super(msg);
	}
}
