package com.yanni.sotrav.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.abstracts.IRoomDao;
import com.yanni.sotrav.dao.abstracts.IRoomUserDao;
import com.yanni.sotrav.models.RoomUser;

@Component("roomUserManagerBean")
public class RoomUserManager implements DataManager<RoomUser, Long>{
	
	@Autowired
	@Qualifier("roomUserDao") //need to declare the dao as a interface or else will get illegal argument exception
	private IRoomUserDao roomUserDao;

	@Override
	public String create(RoomUser roomUsr) {
	    roomUserDao.saveOrUpdate(roomUsr);
	    return "RoomUser succesfully created!";
	}

	@Override
	public RoomUser find(RoomUser roomUsr) {
		// TODO Auto-generated method stub
		return roomUserDao.find(roomUsr.getId());
	}

	@Override
	public RoomUser find(Long id) {
		// TODO Auto-generated method stub
		return roomUserDao.find(id);
	}

	@Override
	public RoomUser find(String identity) {
		// TODO Auto-generated method stub
		return roomUserDao.find(identity);
	}

	@Override
	public List<RoomUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomUser> findByCriteria(Object c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RoomUser roomUsr) {
		roomUserDao.saveOrUpdate(roomUsr);
	}

	@Override
	public void delete(RoomUser roomUsr) {
		roomUserDao.delete(roomUsr);
		
	}

}
