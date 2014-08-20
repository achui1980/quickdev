package com.achui.quick.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.list.TransformedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysUser;
import com.achui.quick.domain.SysUserRole;

@Service("authService")
public class AuthService {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="permissionService")
	private PermissionService permissionService;
	
	@Resource(name="roleResourcePermissionService")
	private RoleResourcePermissionService roleResourcePermissionService;
	
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleString(SysUser user){
		List<SysUserRole> userRoles = userRoleService.findByUserId(user.getId());
		List<Integer> ids = new ArrayList<>();
		for(SysUserRole sysUserRole : userRoles){
			ids.add(sysUserRole.getId());
		}
		List<SysRole> sysRoles = userRoleService.getRoles(ids); 
		List<String> roles = new ArrayList<>();
		for(SysRole role : sysRoles){
			roles.add(role.getRole());
		}
		return roles;
	}
	
	
}
