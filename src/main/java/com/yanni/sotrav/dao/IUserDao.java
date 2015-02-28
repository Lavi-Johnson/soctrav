package com.yanni.sotrav.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.yanni.sotrav.models.User;

public interface IUserDao extends GenericDao<User, Long>{
	  @SuppressWarnings("unchecked")
	  public List<User> getAll();
	  public User getByEmail(String email);
}