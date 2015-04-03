package com.yanni.sotrav.services.room;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.BaseService;


@Component("baseRoomService")
@Service
public class BaseRoomService implements BaseService{
	
	@Autowired
	@Qualifier("roomManagerBean")
	protected DataManager _roomManager;

	@Override
	public Object find(Serializable id) {
		// TODO Auto-generated method stub
		return (Room) _roomManager.find((Long)id);
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return _roomManager.find((Room)obj);
	}

	@Override
	public Object find(Object obj) {
		// TODO Auto-generated method stub
		Room r=(Room) obj;
		return _roomManager.find(r.getId());
	}

	@Override
	public void Update(Object room) {
		// TODO Auto-generated method stub
		_roomManager.update((Room)room);
	}

	@Override
	public void delete(Object room) {
		_roomManager.delete((Room) room);
		
	}

	@Override
	public List findAll() {
		return _roomManager.findAll();
	}

	@Override
	public List findByCriteria(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
