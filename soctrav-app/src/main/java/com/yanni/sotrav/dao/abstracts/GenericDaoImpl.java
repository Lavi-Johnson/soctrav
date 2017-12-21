package com.yanni.sotrav.dao.abstracts;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class UserDao
 * <br />
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO without any XML configuration and also provide the Spring 
 * exception translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public abstract class GenericDaoImpl<T, I extends Serializable> implements GenericDao<T, I>{

    private Class<T> type;
    @PersistenceContext
    private EntityManager _entityManager;
    
    public EntityManager getEntityManager(){
    	return _entityManager;
    }
    
    public Class<T> getType() {
        return type;
    }

    public GenericDaoImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public T find(I id) {
        return (T) _entityManager.find(getType(), id);
    }

    @Override
    public void delete(T obj) {
    	_entityManager.remove(obj);
    }

    @Override
    public void saveOrUpdate(T obj) {
    	_entityManager.merge(obj);
    }
}