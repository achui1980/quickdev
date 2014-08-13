package com.achui.quick.repository;

import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.domain.User;

public interface UserRepository extends BaseRepository<User, Integer>{
	public User findByUsername(String userName);
}
