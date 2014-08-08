package com.achui.quick.common.repository;


import java.util.List;

import com.achui.quick.common.domain.User;

public interface UserRespository extends BaseRepository<User, Integer>{

	public List<User> findByUsername(String userName);
	
}
