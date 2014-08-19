package com.achui.quick.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysUser;
import com.achui.quick.domain.SysUserRole;

public class AuthService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleResourcePermissionService roleResourcePermissionService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleString(SysUser user){
		List<SysUserRole> userRoles = userRoleService.findByUserId(user.getId());
		List<Integer> ids = ListUtils.transformedList(userRoles, new Transformer() {
			@Override
			public Object transform(Object userRole) {
				// TODO Auto-generated method stub
				return ((SysUserRole)userRole).getRoleId();
			}
		});
		List<SysRole> sysRoles = userRoleService.getRoles(ids); 
		List<String> roles = ListUtils.transformedList(sysRoles, new Transformer() {
			@Override
			public Object transform(Object userRole) {
				SysRole role = (SysRole)userRole;
				return role.getRole();
			}
		});
		
		return roles;
	}
	
	
}
