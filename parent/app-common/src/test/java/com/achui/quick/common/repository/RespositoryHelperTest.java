package com.achui.quick.common.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.achui.quick.common.domain.TestUser;
import com.achui.quick.common.test.BaseIT;

public class RespositoryHelperTest extends BaseIT {

	  @PersistenceUnit
	    private EntityManagerFactory entityManagerFactory;

	    private RespositoryHelper repositoryHelper;

	    @Before
	    public void setUp() {
	    	RespositoryHelper.setEntityManagerFactory(entityManagerFactory);
	        repositoryHelper = new RespositoryHelper(TestUser.class);
	    }
	    @Test
	    public void testGetEntityManager() {
	        Assert.assertNotNull(repositoryHelper.getEntityManager());
	    }
	    
	    @Test
	    public void testCount(){
	    	String ql = "select count(obj) from User obj where obj.id = :id";
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put("id", new Integer("2"));
	    	Long countLong = repositoryHelper.count(ql,params);
	    	Assert.assertEquals(0, countLong.longValue());
	    }
	    
	    @Test
	    @Rollback(value = false)
	    public void testBatchUpdate(){
	    	String ql = "update User obj set obj.username = ?1 where obj.id=?2";
	    	int result = repositoryHelper.batchUpdate(ql, "1234", new Integer("1"));
	    	Assert.assertEquals(1, result);
	    }
	    
	    @Test
	    public void testFindAll(){
	    	String ql = "select obj from User obj";
	    	List<TestUser> users = repositoryHelper.findAll(ql, new PageRequest(0, 3), (Map)null);
	    	System.out.println(users.size());
	    }
	    
	    @Test
	    public void testDelete(){
	    }

}
