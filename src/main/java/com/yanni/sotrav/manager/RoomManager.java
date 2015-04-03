package com.yanni.sotrav.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.LocationDao;
import com.yanni.sotrav.dao.RoomDao;
import com.yanni.sotrav.dao.abstracts.IRoomDao;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;

@Component("roomManagerBean")
public class RoomManager implements DataManager<Room, Long>{
	
	@Autowired
	@Qualifier("roomDao") //need to declare the dao as a interface or else will get illegal argument exception
	private IRoomDao roomDao;

	@Override
	public String create(Room room) {
	    try {
	    	roomDao.saveOrUpdate(room);
	    }
	    catch (Exception ex) {
	      return "Error creating the room: " + ex.toString();
	    }
	    return "room succesfully created!";
	}

	@Override
	public Room find(Room room) {
		// TODO Auto-generated method stub
		return roomDao.find(room.getId());
	}

	@Override
	public Room find(Long id) {
		// TODO Auto-generated method stub
		return roomDao.find(id);
	}

	@Override
	public Room find(String identity) {
		// TODO Auto-generated method stub
		return roomDao.find(identity);
	}

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findByCriteria(String c) {
		// TODO Auto-generated method stub
		return roomDao.getAll();
	}

	@Override
	public void update(Room obj) {
		roomDao.saveOrUpdate(obj);
		
	}

	@Override
	public void delete(Room obj) {
		roomDao.delete(obj);
	}

}
