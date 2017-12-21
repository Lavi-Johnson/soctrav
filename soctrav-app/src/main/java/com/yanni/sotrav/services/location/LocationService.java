package com.yanni.sotrav.services.location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.LocationManager;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;


@Component("locationService")
public class LocationService implements IWebService, ILocationService{

	@Autowired
	@Qualifier("locationManagerBean")
	protected DataManager _locationManager;
	
	@Autowired
	@Qualifier("roomManagerBean")
	protected DataManager _roomManager;
	
	@Override
	public Object process(HttpServletRequest request) {
		LocationManager lm=(LocationManager) _locationManager;
		Location loc=new Location();
		ServiceBeanMapper.mapBean(loc, request);
		lm.create(loc);
		return lm.findByNameLocation(loc.getLocation_name(), loc.getCity(), loc.getProvince_name());
	}
	
	@Override
	public Set<Location> getUserLocations(User usr){
		List<Room> rs=_roomManager.findByCriteria(usr);
		Set<Location> locs= new HashSet<Location>();
		for(Room r:rs)if(r.getLocation()!=null)
						locs.add(r.getLocation());
		return locs;
	}

}
