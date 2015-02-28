package com.yanni.sotrav.dao;

import java.util.List;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

public class LocationDao extends GenericDaoImpl<Location, Long>{
	  /**
	   * Method getAll
	   * <br/>
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Message> getAll() {
	    return getEntityManager().createQuery("from Location").getResultList();
	  }

	@Override
	public Location find(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
