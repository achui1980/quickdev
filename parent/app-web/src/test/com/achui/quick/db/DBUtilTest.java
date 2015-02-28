package com.achui.quick.db;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;

import com.achui.quick.common.test.BaseIT;
import com.achui.quick.domain.SysRoleResourcePermission;
import com.achui.quick.domain.SysUser;
import com.achui.quick.domain.Word;

public class DBUtilTest extends BaseIT{

	@Resource
	DataSource dataSource;
	@Test
	public void testGetMetaInfo() {
		Map<String,ColumnMeta> info = DBUtil.getMetaInfo(dataSource, SysRoleResourcePermission.class);
		for(Map.Entry<String, ColumnMeta> meta : info.entrySet()){
			System.out.println("Column Name:"+meta.getKey()+"		getTypeName:"+meta.getValue().getTypeName());
		}
	}

}
