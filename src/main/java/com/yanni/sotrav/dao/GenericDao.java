package com.yanni.sotrav.dao;

import java.io.Serializable;



public interface GenericDao<T, I extends Serializable> {
    public T find(I id);
    public void delete(T obj);
    public void saveOrUpdate(T obj);
}
