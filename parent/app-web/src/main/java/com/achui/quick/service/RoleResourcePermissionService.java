package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysRoleResourcePermission;

@Service("roleResourcePermissionService")
@Transactional
public class RoleResourcePermissionService extends BaseService<SysRoleResourcePermission, Integer>{
	
}
