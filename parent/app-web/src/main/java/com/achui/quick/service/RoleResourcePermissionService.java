package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysRoleResourcePermission;

@Service
@Transactional
public class RoleResourcePermissionService extends BaseService<SysRoleResourcePermission, Integer>{

}
