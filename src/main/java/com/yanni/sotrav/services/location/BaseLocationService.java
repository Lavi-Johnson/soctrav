package com.yanni.sotrav.services.location;

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
import com.yanni.sotrav.services.BaseService;

@Component("baseLocationService")
@Service
public class BaseLocationService implements BaseService{
	
	@Autowired
	@Qualifier("locationManagerBean")
	protected DataManager _locationManager;

	@Override
	public Object find(Serializable id) {
		return (Location) _locationManager.find((Long)id);
	}

	@Override
	public Object create(Object loc) {
		return _locationManager.create((Location)loc);
	}

	@Override
	public Object find(Object loc) {
		Location location=(Location)loc;
		return (Location) _locationManager.find(location.getId());
	}

	@Override
	public void Update(Object loc) {
		Location location=(Location)loc;
		_locationManager.update(loc);
	}

	@Override
	public void delete(Object usr) {
		// TODO Auto-generated method stub
		_locationManager.delete(usr);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return _locationManager.findAll();
	}

	@Override
	public List findByCriteria(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
