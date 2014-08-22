package com.achui.quick.util;

import org.apache.shiro.SecurityUtils;

public class PermissionUtil {

	public static boolean checkPermission(String permission){
		return SecurityUtils.getSubject().isPermitted(permission);
	}
}
