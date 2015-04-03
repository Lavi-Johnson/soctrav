package com.yanni.sotrav.dao.abstracts;

import java.io.Serializable;



public interface GenericDao<T, I extends Serializable> {
    public T find(I id);
    public T find(String identity);
    public void delete(T obj);
    public void saveOrUpdate(T obj);
}
