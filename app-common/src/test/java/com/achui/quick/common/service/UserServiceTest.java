package com.achui.quick.common.service;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.common.test.BaseIT;

public class UserServiceTest extends BaseIT{

	@Autowired
	private UserService userService;
	
	@Test
	public void testDeltetUser(){
		userService.delte(Arrays.asList(new Integer(4)));
	}
}
