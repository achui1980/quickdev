package com.achui.quick.repository;

import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.domain.SysUser;

public interface UserRepository extends BaseRepository<SysUser, Integer>{
	public SysUser findByUsername(String userName);
}
