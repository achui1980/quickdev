package com.achui.quick.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.domain.SysPermission;
import com.achui.quick.domain.SysRole;
import com.achui.quick.domain.SysUser;
import com.achui.quick.domain.SysUserRole;

public interface UserRoleRepository extends BaseRepository<SysUserRole, Integer>{

	public List<SysUserRole> findByUserId(Integer userId);
	
	@Query("from SysRole obj where obj.id in(?1)")
	public List<SysRole> getRolesByIds(List<Integer> ids);
	
	public List<SysPermission> getPermissions(SysUser user);
	
}
