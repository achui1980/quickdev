package com.achui.quick.common.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.achui.quick.common.entity.AbstractEntity;
import com.achui.quick.common.repository.BaseRepository;

@Transactional
public abstract class BaseService<M, ID extends Serializable> {
	
	protected BaseRepository<M, ID> baseRepository;

	@Autowired
	public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	/**
	 * 保存实体
	 * @param m
	 * @return
	 */
	public M save(M m){
		return baseRepository.save(m);
	}
	
	public M saveAndFlush(M m){
		m = save(m);
		baseRepository.flush();
		return m;
	}

	public M update(M m){
		return baseRepository.save(m);
	}
	
	public void delete(ID id){
		baseRepository.delete(id);
	}
	
	public void deleteInBatch(Iterable<M> entities){
		baseRepository.deleteInBatch(entities);
	}
	
	public void delete(List<ID> ids){
		baseRepository.delete(ids);
	}
	
	
	public M findOne(ID id){
		return baseRepository.findOne(id);
	}
	
	public boolean exists(ID id){
		return baseRepository.exists(id);
	}
	
	public List<M> findAll(String ql, Pageable pageable, Map<String, Object> paramsMap){
		return baseRepository.findAll(ql, pageable, paramsMap);
	}
	
	public List<M> findAll(String ql, Pageable pageable, Object...params){
		return baseRepository.findAll(ql, pageable, params);
	}
	
	public Long count(String ql,Map<String, Object> paramMap){
		return baseRepository.count(ql, paramMap);
	}
	
	public Long count(String ql,Object... params){
		return baseRepository.count(ql, params);
	}
	
	public List<M> findAll(List ids){
		return baseRepository.findAll(ids);
	}
	
	public List<M> save(List<M> records){
		records = baseRepository.save(records);
		baseRepository.flush();
		return records;
	}
	
	public int batchUpdate(String ql, Object... params){
		return baseRepository.batchUpdate(ql, params);
	}
	
	public int batchUpdate(String ql, Map<String, Object> paramsMap){
		return baseRepository.batchUpdate(ql, paramsMap);
	}
	
	public M findOne(String ql,Sort sort,Object...params ){
		return baseRepository.findOne(ql, sort, params);
	}
	
	public M findOne(String ql,Sort sort,Map<String, Object> paramsMap){
		return baseRepository.findOne(ql, sort, paramsMap);
	}
	
	public List<M> findAll(){
		return baseRepository.findAll();
	}
}
