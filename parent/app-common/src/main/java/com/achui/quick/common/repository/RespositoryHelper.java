package com.achui.quick.common.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.util.Assert;


public class RespositoryHelper {

	 private static EntityManager entityManager;
	 private Class<?> entityClass;
	 
	 public RespositoryHelper(Class<?> entityClass){
		 this.entityClass = entityClass;
	 }
	 
    public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        entityManager = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
    }
    
    public static EntityManager getEntityManager() {
        Assert.notNull(entityManager, "entityManager must null, please see " +
                "[com.sishuok.es.common.repository.RepositoryHelper#setEntityManagerFactory]");

        return entityManager;
    }
    
    public static void flush(){
    	getEntityManager().flush();
    }
    
    public static void clear(){
    	flush();
    	getEntityManager().clear();
    }
    
    /**
     * 根据条件查询符合条件的记录，分页显示
     * @param ql
     * @param pageable
     * @param params
     * @return
     */
    public <M> List<M> findAll(String ql,Pageable pageable, Object...params ){
    	String prepareQL = prepareOrder(pageable == null?null:pageable.getSort());
    	Query query = getEntityManager().createQuery(ql+prepareQL);
    	setParameters(query,params);
    	if(pageable != null){
    		query.setFirstResult(pageable.getOffset());
    		query.setMaxResults(pageable.getPageSize());
    	}
    	return query.getResultList();
    }
    
    public <M> List<M> findAll(String ql, Pageable pageable, Map<String, Object> paramsMap){
    	String prepareQL = prepareOrder(pageable == null?null:pageable.getSort());
    	Query query = getEntityManager().createQuery(ql+prepareQL);
    	setParameters(query, paramsMap);
    	if(pageable != null){
    		query.setFirstResult(pageable.getOffset());
    		query.setMaxResults(pageable.getPageSize());
    	}
    	return query.getResultList();
    	
    }
    
    /**
     * 不分页查询
     * @param ql
     * @param sort null 表示不排序
     * @param paramsMap
     * @return
     */
    public <M> List<M> findAll(String ql,Sort sort,Map<String, Object> paramsMap){
    	String prepareOrder = prepareOrder(sort);
    	Query query = getEntityManager().createQuery(ql + prepareOrder);
    	setParameters(query, paramsMap);
    	return query.getResultList();
    }
    
    /**
     * 不分页查询
     * @param ql
     * @param sort null 表示不排序
     * @param paramsMap
     * @return
     */
    public <M> List<M> findAll(String ql,Sort sort,Object...params){
    	String prepareOrder = prepareOrder(sort);
    	Query query = getEntityManager().createQuery(ql + prepareOrder);
    	setParameters(query, params);
    	return query.getResultList();
    }
    
    /**
     * 查询记录数
     * @param ql
     * @param paramsMap
     * @return
     */
    public Long count(String ql,Map<String, Object> paramsMap){
    	Query query = getEntityManager().createQuery(ql);
    	setParameters(query, paramsMap);
    	return (Long)query.getSingleResult();
    }
    
    public Long count(String ql,Object... params){
    	Query query = getEntityManager().createQuery(ql);
    	setParameters(query, params);
    	return (Long)query.getSingleResult();
    }
    
    /**
     * 查询一条记录
     * @param ql
     * @param sort null 为不排序
     * @param paramsMap
     * @return
     */
    public <M> M findOne(String ql,Sort sort,Map<String, Object> paramsMap){
    	Pageable pageable = new PageRequest(0, 1, sort);
    	List<M> list = findAll(ql, pageable, paramsMap);
    	if(list.size() >0){
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * 查询一条记录
     * @param ql
     * @param sort null 为不排序
     * @param paramsMap
     * @return
     */
    public <M> M findOne(String ql,Sort sort,Object...params ){
    	Pageable pageable = new PageRequest(0, 1, sort);
    	List<M> list = findAll(ql, pageable, params);
    	if(list.size() >0){
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * 批量更新，insert,update,delete等ddl操作
     * @param ql
     * @param paramsMap
     * @return
     */
    public int batchUpdate(String ql, Map<String, Object> paramsMap){
    	Query query = getEntityManager().createQuery(ql);
    	setParameters(query, paramsMap);
    	return query.executeUpdate();
    }
    
    /**
     * 批量更新，insert,update,delete等ddl操作
     * @param ql
     * @param paramsMap
     * @return
     */
    @Transactional
    public int batchUpdate(String ql, Object... params){
    	Query query = getEntityManager().createQuery(ql);
    	setParameters(query, params);
    	return query.executeUpdate();
    }
    
    private void setParameters(Query query,Object[] params){
    	if(params != null){
    		for(int i = 0; i < params.length; i++){
    			query.setParameter(i+1, params[i]);
    		}
    	}
    }
    
    private void setParameters(Query query,Map<String, Object> paramsMap){
    	if(paramsMap != null && !paramsMap.isEmpty()){
    		for(Entry<String, Object> entry: paramsMap.entrySet()){
    			String paramName = entry.getKey();
    			Object paramValue = entry.getValue();
    			query.setParameter(paramName, paramValue);
    		}
    	}
    }
    
    /**
     * 默认按主键倒叙排列
     * @param sort
     * @return
     */
    private String prepareOrder(Sort sort){
    	Sort newSort = sort;
    	//默认排序
    	if(newSort == null || !newSort.iterator().hasNext()){
    		Iterator<String> idIterator = getMetadata(entityClass).getIdAttributeNames().iterator();
        	List<Order> orderList = new ArrayList<Sort.Order>();
        	while(idIterator.hasNext()){
        		String id = idIterator.next();
        		Order order = new Order(Direction.DESC, id);
        		orderList.add(order);
        	}
        	newSort = new Sort(orderList);
    	}
    	StringBuilder sb = new StringBuilder("");
    	sb.append(" order by ");
    	sb.append(newSort.toString().replace(":", " "));
    	return sb.toString();
    }
    
    public <T> JpaEntityInformation<T, ?> getMetadata(Class<T> entityClass) {
        return JpaEntityInformationSupport.getMetadata(entityClass, entityManager);
    }

    public String getEntityName() {
        return getMetadata(entityClass).getEntityName();
    }
    
    
}
