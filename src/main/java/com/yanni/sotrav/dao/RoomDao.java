package com.yanni.sotrav.dao;

import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

public class RoomDao extends GenericDaoImpl<Room, Long>{

	  /**
	   * Method getAll
	   * <br/>
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Room> getAll() {
	    return getEntityManager().createQuery("from Room").getResultList();
	  }

	@Override
	public Room find(String identity) {
		// TODO Auto-generated method stub
		return null;
	}
}
