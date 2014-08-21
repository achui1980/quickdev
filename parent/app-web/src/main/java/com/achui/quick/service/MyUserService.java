package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysUser;
import com.achui.quick.repository.MyUserRepository;

@Service("myuserService")
@Transactional
public class MyUserService extends BaseService<SysUser, Integer>{

	@Autowired 
	private MyUserRepository userRepository;
//    private UserRepository getUserRepository() {
//        return (UserRepository) baseRepository;
//    }
	
	public SysUser findByUsername(String userName){
		return this.userRepository.findByUsername(userName);
	}
}
