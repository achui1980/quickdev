package com.achui.quick.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.SysPermission;
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
	
	@Autowired
	private PermissionService permissionService;
	
	
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
		List<String> permissions = new ArrayList<String>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		for(SysRole role : roleList){
			paramsMap.clear();
			paramsMap.put("roleId", role.getId());
			String ql = " from SysRoleResourcePermission obj where obj.roleId = :roleId";
			List<SysRoleResourcePermission> resourcePermissions = userRoleRepository.findAll(ql,(Sort)null, paramsMap);
			for(SysRoleResourcePermission rrp : resourcePermissions){
				SysResource resource = resourceService.findOne(rrp.getResourceId());
				String realModule = getRealModule(resource);
				List<Integer> ids = transformToList(rrp.getPermissionIds());
				for(SysPermission permission : permissionService.findAll(ids)){
					permissions.add(realModule+":"+permission.getOp());
				}
			}
			System.out.println(permissions);
		}
		return permissions;
	}
	
	private String getRealModule(SysResource resource){
		if(resource  == null) return null;
		StringBuffer sb = new StringBuffer(resource.getModule());
		boolean hasResouce = !StringUtils.isEmpty(resource.getModule());
		SysResource parent = resourceService.findOne(resource.getParentId());
		while(parent != null){
			if(!StringUtils.isEmpty(parent.getModule())){
				sb.insert(0, parent.getModule()+":");
				hasResouce = true;
			}
			if(parent.getParentId() == null) 
				parent=null;
			else
				parent = resourceService.findOne(parent.getParentId());
		}
		if(!hasResouce){
			return "";
		}
		
		 //如果最后一个字符是: 因为不需要，所以删除之
        int length = sb.length();
        if(length > 0 && sb.lastIndexOf(":") == length - 1) {
            sb.deleteCharAt(length - 1);
        }
        
        boolean hasChild = false;
        for(SysResource r : resourceService.findAll()){
        	if(resource.getId().equals(r.getParentId())){
        		hasChild = true;
        		break;
        	}
        }
        if(hasChild){
        	sb.append(":*");
        }
		return sb.toString();
	}
	

	private List<Integer> transformToList(String str){
		String[] strs = str.split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for(String s : strs){
			ids.add(Integer.valueOf(s));
		}
		return ids;
	}
}
