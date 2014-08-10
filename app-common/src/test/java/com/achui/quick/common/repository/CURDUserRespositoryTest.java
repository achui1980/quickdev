package com.achui.quick.common.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.common.domain.User;
import com.achui.quick.common.test.BaseIT;

public class CURDUserRespositoryTest extends BaseIT{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRespository userRespository;
	
	private User user;
	
	@Before
	public void setUp(){
		user = new User();
		user.setPassword("1234");
		user.setUsername(RandomStringUtils.randomAlphabetic(6));
	}
	
	@Test
	@Ignore
	public void testSaveUser(){
		userRespository.save(user);
	}
	@Test 
	@Ignore
	public void testFindUser(){
		List<User> users = userRespository.findByUsername("achui4");
		org.springframework.util.Assert.notNull(users);
		junit.framework.Assert.assertEquals(1,users.size());
	}
	
	@Test 
	@Ignore
	public void testDeleteUser(){
		userRespository.delete(Arrays.asList(new Integer(2),new Integer(3),new Integer(1)));
	}
	
	@Test
	public void testSaveOrUpdate(){
		User user = new User();
		user.setPassword("11111");
		List<User> list = new ArrayList<User>();
		list.add(user);
		userRespository.saveorupdateAll(list);
	}
	
}
