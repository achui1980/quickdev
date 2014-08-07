package com.achui.quick.common.repository.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.CollectionUtils;

import com.achui.quick.common.entity.AbstractEntity;
import com.achui.quick.common.entity.Searchable;
import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.common.repository.RespositoryHelper;

public class DefaultBaseRepository<M extends AbstractEntity,ID extends Serializable> extends SimpleJpaRepository<M, ID> 
implements BaseRepository<M, ID>{
	
    public static final String LOGIC_DELETE_ALL_QUERY_STRING = "update %s x set x.deleted=true where x in (?1)";
    public static final String DELETE_ALL_QUERY_STRING = "delete from %s x where x in (?1)";
    public static final String FIND_QUERY_STRING = "from %s x where 1=1 ";
    public static final String COUNT_QUERY_STRING = "select count(x) from %s x where 1=1 ";

    private final EntityManager em;
    private final JpaEntityInformation<M, ID> entityInformation;
    private RespositoryHelper respositoryHelper;
    private Class<M> entityClass;
    private String idName;
    private String entityName;
    
	public DefaultBaseRepository(JpaEntityInformation<M, ID> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.entityInformation = entityInformation;
		this.entityClass = this.entityInformation.getJavaType();
		respositoryHelper = new RespositoryHelper(entityClass);
		 this.idName = this.entityInformation.getIdAttributeNames().iterator().next();
		 this.entityName = this.entityInformation.getEntityName();
	}

	@Transactional
	public void delete(List<ID> ids) {
		// TODO Auto-generated method stub
		if(CollectionUtils.isEmpty(ids)){
			return ;
		}
		List<M> models = new ArrayList<M>();
		for(ID id : ids){
			M model = null;
			try {
				model = entityClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("batch delete "+entityClass+" error",e);
			}
			try {
				BeanUtils.setProperty(model, idName, id);
			} catch (Exception e) {
				throw new RuntimeException("batch delete " + entityClass + " error, can not set id", e);
			}
			models.add(model);
		}
		
		batchDelete(models);
	}
	
	@Transactional
	private void batchDelete(List<M> models){
		if(CollectionUtils.isEmpty(models)){
			return;
		}
		String ql = String.format(DELETE_ALL_QUERY_STRING, entityName);
		respositoryHelper.batchUpdate(ql, models);
		
	}
	public Page<M> findAll(Searchable searchable) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count(Searchable searchable) {
		// TODO Auto-generated method stub
		return 0;
	}

}
