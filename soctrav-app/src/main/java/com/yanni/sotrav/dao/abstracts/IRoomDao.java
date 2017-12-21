package com.yanni.sotrav.dao.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

public interface IRoomDao extends GenericDao<Room, Long>{
	  public List<Room> getAll();
	  public List<Room> findAllUserRoom(User usr);
	  public Room findFirstRoom(Long usr, Long loc);
}
