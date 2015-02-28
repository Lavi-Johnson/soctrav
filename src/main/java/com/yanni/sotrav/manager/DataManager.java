package com.yanni.sotrav.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

public interface DataManager<T, I extends Serializable> {
	
	public String create(T obj);
	
	public T find(T obj);
	
	public T find(I id);
	
	public T find(String identity);
	
	public List<T> findAll();
	
	public List<T> findByCriteria(String c);
	
	public void update(T obj);
	
	public void delete(T obj);

}
