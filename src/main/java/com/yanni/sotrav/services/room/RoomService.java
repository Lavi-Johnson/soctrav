package com.yanni.sotrav.services.room;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("createRoomService")
public class CreateRoomService implements IWebService{
	
	@Autowired
	@Qualifier("roomManagerBean")
	protected DataManager _roomManager;
	
	public String create(HttpServletRequest request, Location loc) {
		Room room=new Room();
		room.setLocation_id(loc.getId());
		ServiceBeanMapper.mapBean(room, request);
		return _roomManager.create(room);
	}

	@Override
	public Object process(HttpServletRequest request) {
		Room room=new Room();
		ServiceBeanMapper.mapBean(room, request);
		Location loc=(Location) request.getAttribute("_location");
		//room.setUser_id(user_id);
		//request.setAttribute("_location", loc);
		HttpSession session=request.getSession();
		User usr =(User) session.getAttribute("userLogin");
		room.setLocation_id(loc.getId());
		room.setUser_id(usr.getId());
		return _roomManager.create(room);
	}

}
