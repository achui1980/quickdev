package com.achui.quick.common.repository;


import java.io.FileWriter;
import java.util.List;

import com.achui.quick.common.domain.TestUser;

public interface UserRespository extends BaseRepository<TestUser, Integer>{

	public List<TestUser> findByUsername(String userName);
	
}
