package com.achui.quick.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.achui.quick.common.entity.AbstractEntity;
import com.achui.quick.common.repository.BaseRepository;

public abstract class BaseService<M extends AbstractEntity, ID extends Serializable> {
	
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
	
	public void delte(List<ID> ids){
		baseRepository.delete(ids);
	}
	
	public M findOne(ID id){
		return baseRepository.findOne(id);
	}
	
	public boolean exists(ID id){
		return baseRepository.exists(id);
	}
}
