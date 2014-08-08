package com.achui.quick.common.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;

import com.achui.quick.common.repository.BaseRepository;


public class DefaultBaseRepositoryFactoryBean<R extends JpaRepository<M, ID>, M, ID extends Serializable>
extends JpaRepositoryFactoryBean<R, M, ID> {
	
	public DefaultBaseRepositoryFactoryBean(){
		
	}

	 protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
	        return new DefaultBaseRepositoryFactory(entityManager);
	}
	 
	class DefaultBaseRepositoryFactory<M, ID extends Serializable> extends JpaRepositoryFactory{
		private EntityManager entityManager;

	    public DefaultBaseRepositoryFactory(EntityManager entityManager) {
	        super(entityManager);
	        this.entityManager = entityManager;
	    }
	    protected Object getTargetRepository(RepositoryMetadata metadata) {
	        Class<?> repositoryInterface = metadata.getRepositoryInterface();

	        if (isBaseRepository(repositoryInterface)) {

	            JpaEntityInformation<M, ID> entityInformation = getEntityInformation((Class<M>) metadata.getDomainType());
	            DefaultBaseRepository repository = new DefaultBaseRepository(entityInformation, entityManager);

	            return repository;
	        }
	        return super.getTargetRepository(metadata);
	    }

	    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
	        if (isBaseRepository(metadata.getRepositoryInterface())) {
	            return DefaultBaseRepository.class;
	        }
	        return super.getRepositoryBaseClass(metadata);
	    }

	    private boolean isBaseRepository(Class<?> repositoryInterface) {
	        return BaseRepository.class.isAssignableFrom(repositoryInterface);
	    }

	    @Override
	    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
	        return super.getQueryLookupStrategy(key);
	    }
	}
}
