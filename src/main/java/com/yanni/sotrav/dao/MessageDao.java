package com.yanni.sotrav.dao;

import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

public class MessageDao extends GenericDaoImpl<Message, Long>{
	
	  /**
	   * Method getAll
	   * <br/>
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Message> getAll() {
	    return getEntityManager().createQuery("from Message").getResultList();
	  }

}
