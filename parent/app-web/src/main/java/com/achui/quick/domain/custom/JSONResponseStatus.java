package com.achui.quick.domain.custom;

import java.util.HashMap;
import java.util.Map;

public class JSONResponseStatus {
	public enum Status {
		OK (200, "OK"),
		NOT_FOUND (404, "Not Found"),
		BAD_REQUEST (400, "Bad Request"),
		INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
		IMAGE_TOO_LARGE (1, "Image Too Large"),
		IMAGE_ERROR_EXTENSION(2,"Error extension");
		
		private int statusCode;
		private String name;
		private static Map<Integer, String> codeToStatusMapping;
		
		private Status(int statusCode, String name) {
			this.statusCode = statusCode;
			this.name = name;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public String getName() {
			return name;
		}
		
		public static String getStatusNameByCode(int statusCode) {
			if (codeToStatusMapping == null) {
				initMapping();
			}
			return codeToStatusMapping.get(statusCode);
		}
		
		private static void initMapping() {
	        codeToStatusMapping = new HashMap<Integer, String>();
	        for (Status s : values()) {
	            codeToStatusMapping.put(s.statusCode, s.name);
	        }
	    }
	}
}
