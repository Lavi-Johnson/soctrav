package com.yanni.sotrav.services.location;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.LocationManager;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.services.IWebService;


@Component("createLocationService")
public class  CreateLocationService implements IWebService{

	@Autowired
	@Qualifier("locationManagerBean")
	protected DataManager _locationManager;
	
	@Override
	public Object process(HttpServletRequest request) {
		LocationManager lm=(LocationManager) _locationManager;
		Location loc=new Location();
		ServiceBeanMapper.mapBean(loc, request);
		lm.create(loc);
		return lm.findByNameLocation(loc.getLocation_name(), loc.getCity(), loc.getProvince_name());
	}

}
