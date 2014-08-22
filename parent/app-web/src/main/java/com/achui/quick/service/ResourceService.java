package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysResource;

@Service("resourceService")
@Transactional
public class ResourceService extends BaseService<SysResource, Integer>{

}
