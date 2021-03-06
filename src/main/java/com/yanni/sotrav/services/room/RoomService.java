package com.yanni.sotrav.services.room;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.common.SharedConstants;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.IRoomManager;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("roomService")
public class RoomService implements IWebService, IRoomService{
	
	@Autowired
	@Qualifier("roomManagerBean")
	protected DataManager _genRoomManager;
	
	@Autowired
	@Qualifier("roomManagerBean")
	protected IRoomManager roomManager;
	
	public String create(HttpServletRequest request, Location loc) {
		Room room=new Room();
		//room.setLocation_id(loc.getId());
		room.setLocation(loc);
		ServiceBeanMapper.mapBean(room, request);
		return _genRoomManager.create(room);
	}

	@Override
	public Object process(HttpServletRequest request) {
		Room room=new Room();
		ServiceBeanMapper.mapBean(room, request);
		Location loc=(Location) request.getAttribute(SharedConstants.LOCATION_OBJECT);
		HttpSession session=request.getSession();
		User usr =(User) session.getAttribute("userLogin");
		room.setLocation_id(loc.getId());
		room.setLocation(loc);
		room.setUser_id(usr.getId());
		return _genRoomManager.create(room);
	}
	
	@Override
	public Room getFirstRoom(Long l, Long u) {
		return roomManager.getFirstRoom(l, u);
	}

}
