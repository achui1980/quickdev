package com.achui.quick.common.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.achui.quick.common.entity.Searchable;

@NoRepositoryBean
public interface BaseRepository<M,ID extends Serializable> extends JpaRepository<M, ID> {
	
	/**
	 * 根据主键ID删除
	 * @param ids
	 */
	public void delete(List<ID> ids);
	
	
	/**
	 * 根据查询条件查询结果集，分页显示
	 * @param searchable
	 * @return
	 */
	public Page<M> findAll(Searchable searchable);
	
	
	/**
	 * 根据查询条件获取记录数
	 * @param searchable
	 * @return
	 */
	public long count(Searchable searchable); 
	
	
	
	
	
	

}
