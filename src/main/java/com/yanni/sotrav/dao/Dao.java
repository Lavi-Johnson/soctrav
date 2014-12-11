package com.yanni.sotrav.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class Dao{

    @PersistenceContext
    private EntityManager _entityManager;
    
    public EntityManager getEntityManager(){
    	return _entityManager;
    }
    
    public void saveOrUpdate (Object obj) {
    	_entityManager.merge(obj);
    }
    
    public Object find(Long id, Object type) {
        return _entityManager.find(type.getClass(), id);
    }

    public void delete(Object obj) {
    	_entityManager.remove(obj);
    }

}
