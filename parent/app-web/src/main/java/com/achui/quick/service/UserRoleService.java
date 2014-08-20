package com.achui.quick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysUserRole;
import com.achui.quick.repository.UserRoleRepository;

@Service("userRoleService")
public class UserRoleService extends BaseService<SysUserRole, Integer>{

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public List<SysUserRole> findByUserId(Integer userId){
		return userRoleRepository.findByUserId(userId);
	}
	
	public List<SysRole> getRoles(List<Integer> ids){
		return userRoleRepository.getRolesByIds(ids);
	}

}
