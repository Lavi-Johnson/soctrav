package com.yanni.sotrav.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.LocationDao;
import com.yanni.sotrav.dao.RoomDao;
import com.yanni.sotrav.dao.abstracts.IRoomDao;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

@Component("roomManagerBean")
public class RoomManager implements DataManager<Room, Long>, IRoomManager{
	
	@Autowired
	@Qualifier("roomDao") //need to declare the dao as a interface or else will get illegal argument exception
	private IRoomDao roomDao;

	@Override
	public String create(Room room) {
	    roomDao.saveOrUpdate(room);
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
	public List<Room> findByCriteria(Object c) {
		// TODO Auto-generated method stub
		List<Room> rs=null;
		if(c instanceof User){
			rs=roomDao.findAllUserRoom((User)c);
		}
		return rs;
	}

	@Override
	public void update(Room room) {
		roomDao.saveOrUpdate(room);
		
	}

	@Override
	public void delete(Room room) {
		roomDao.delete(room);
	}

	@Override
	public Room getFirstRoom(Long l, Long u) {
		return roomDao.findFirstRoom(u, l);
	}

}
