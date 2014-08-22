package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.achui.quick.common.domain.TestUser;
import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysPermission;
import com.achui.quick.repository.MyUserRepository;

@Service("permissionService")
@Transactional
public class PermissionService extends BaseService<SysPermission,Integer>{
}
