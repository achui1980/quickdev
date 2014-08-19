package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysUser;
import com.achui.quick.repository.UserRepository;
import com.achui.quick.repository.UserRoleRepository;

@Service("userService")
@Transactional
public class UserService extends BaseService<SysUser, Integer>{

	@Autowired
    private UserRepository getUserRepository() {
        return (UserRepository) baseRepository;
    }
	
	public SysUser findByUsername(String userName){
		return this.getUserRepository().findByUsername(userName);
	}
}
