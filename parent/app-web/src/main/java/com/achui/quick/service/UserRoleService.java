package com.achui.quick.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysPermission;
import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysUser;
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
	
	public List<String> getPermissions(SysUser user){
		/**
		 * 1.user对应的role
		 * 2、role对应的resouce
		 * 3、resouce对应的permission
		 * 格式：sys:user:create 或者 sys:*:create
		 */
		List<SysRole> roleList = this.getRoles(Arrays.asList(user.getId()));
		for(SysRole role : roleList){
			String ql = " from SysRoleResoucePermission obj where obj.roleId = :roleId";
			userRoleRepository.findAll(ql, (Sort)null, null);
		}
		return null;
	}
	

}
