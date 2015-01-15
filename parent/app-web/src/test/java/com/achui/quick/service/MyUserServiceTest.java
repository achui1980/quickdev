//package com.achui.quick.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.junit.Test;
//
//import com.achui.quick.common.test.BaseIT;
//import com.achui.quick.domain.SysUser;
//
//public class MyUserServiceTest extends BaseIT{
//	@Resource(name="authService")
//	private AuthService authService;
//	@Resource(name="myuserService")
//	private MyUserService userService;
//	@Resource
//	private DataSource dataSource;
//	@Test
//	public void testGetRoleString() {
//		SysUser user = new SysUser();
//		user.setId(new Integer(1));
//		user.setPassword("dasdf");
//		List<SysUser> a = new ArrayList<>();
//		a.add(user);
//		userService.saveorupdateAll(a);
//		//Assert.assertEquals(1, roles.size());
//	}
//
//}
