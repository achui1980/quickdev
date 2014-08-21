package com.achui.quick.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysResource;
import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysRoleResourcePermission;
import com.achui.quick.domain.SysUser;
import com.achui.quick.domain.SysUserRole;
import com.achui.quick.repository.ResourceRepository;
import com.achui.quick.repository.UserRoleRepository;

@Service("userRoleService")
public class UserRoleService extends BaseService<SysUserRole, Integer>{

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired 
	private ResourceService resourceService;
	
	
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
		Map<String, Object> paramsMap = new HashMap<>();
		for(SysRole role : roleList){
			paramsMap.clear();
			paramsMap.put("roleId", role.getId());
			String ql = " from SysRoleResourcePermission obj where obj.roleId = :roleId";
			List<SysRoleResourcePermission> resourcePermissions = userRoleRepository.findAll(ql,(Sort)null, paramsMap);
			for(SysRoleResourcePermission rrp : resourcePermissions){
				SysResource resource = resourceService.findOne(rrp.getResourceId());
				System.out.println("");
			}
			System.out.println("");
		}
		return null;
	}
	

}
