package com.achui.quick.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<M,ID extends Serializable> extends JpaRepository<M, ID> {
	
	/**
	 * 根据主键ID删除
	 * @param ids
	 */
	public void delete(List<ID> ids);
	
	public List findAll(String ql,Pageable pageable,Map<String, Object> paramMap);
	
	public List findAll(String ql,Sort sort,Map<String, Object> paramMap);
	
	public Long count(String ql,Map<String, Object> paramMap);
	
	public List findAll(String ql,Pageable pageable,Object... params);
	
	public List findAll(String ql,Sort sort,Object... params);
	
	public <M> M findOne(String ql,Sort sort,Object...params );
	
	public <M> M findOne(String ql,Sort sort,Map<String, Object> paramsMap);
	
	public Long count(String ql,Object... params);
	
	public List<M> saveorupdateAll(List<M> records);
	
	public int batchUpdate(String ql, Object... params);
	
	public int batchUpdate(String ql, Map<String, Object> paramsMap);

}
