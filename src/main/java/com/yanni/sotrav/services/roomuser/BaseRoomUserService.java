package com.yanni.sotrav.services.roomuser;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.services.BaseService;


@Component("baseRoomUserService")
@Service
public class BaseRoomUserService implements BaseService{
	
	@Autowired
	@Qualifier("roomUserManagerBean")
	protected DataManager _roomManager;

	@Override
	public Object find(Serializable id) {
		// TODO Auto-generated method stub
		return _roomManager.find(id);
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return _roomManager.create(obj);
	}

	@Override
	public Object find(Object obj) {
		// TODO Auto-generated method stub
		return _roomManager.find(obj);
	}

	@Override
	public void Update(Object obj) {
		// TODO Auto-generated method stub
		_roomManager.update(obj);
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		_roomManager.delete(obj);;
		
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return _roomManager.findAll();
	}

	@Override
	public List findByCriteria(Object crit) {
		// TODO Auto-generated method stub
		return _roomManager.findByCriteria(crit);
	}

}
