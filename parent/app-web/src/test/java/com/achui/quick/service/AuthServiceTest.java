package com.achui.quick.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;

import com.achui.quick.common.test.BaseIT;
import com.achui.quick.domain.SysUser;

public class AuthServiceTest extends BaseIT{
	@Resource(name="authService")
	private AuthService authService;
	@Resource(name="myuserService")
	private MyUserService userService;
	@Resource
	private DataSource dataSource;
	@Test
	public void testGetRoleString() {
		SysUser user = new SysUser();
		user.setId(new Integer(1));
		List roles = authService.getPermissions(user);
		List obj = authService.getRoleString(user);
		//Assert.assertEquals(1, roles.size());
	}

}
