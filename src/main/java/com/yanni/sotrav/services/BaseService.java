package com.yanni.sotrav.services;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.models.User;

public interface BaseService <T, I extends Serializable>{
	
	public T find(I id);
	
	public T create(Object obj);
	
	public T find(Object obj);
	
	public void Update(T obj);
	
	public void delete(T obj);
	
	public List<T> findAll();

	public List<T> findByCriteria(Object crit);

}
