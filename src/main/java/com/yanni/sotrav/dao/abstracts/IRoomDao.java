package com.yanni.sotrav.dao.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;

public interface IRoomDao extends GenericDao<Room, Long>{
	  public List<Room> getAll();
}
