package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysPermission;

@Service("permissionService")
@Transactional
public class PermissionService extends BaseService<SysPermission,Integer>{
}
