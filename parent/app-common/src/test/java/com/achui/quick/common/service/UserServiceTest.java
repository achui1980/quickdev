package com.achui.quick.common.service;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.common.test.BaseIT;

public class UserServiceTest extends BaseIT{

	@Autowired
	private TestUserService userService;
	
	@Test
	public void testDeltetUser(){
		userService.delete(Arrays.asList(new Integer(4)));
	}
}
