package com.yanni.sotrav.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.LocationDao;
import com.yanni.sotrav.dao.abstracts.ILocationDao;
import com.yanni.sotrav.models.Location;


@Component("locationManagerBean")
public class LocationManager implements DataManager<Location, Long>{
	
	@Autowired
	@Qualifier("locationDao") //need to declare the dao as a interface or else will get illegal argument exception
	private ILocationDao locDao;

	@Override
	public String create(Location loc) {
		// TODO Auto-generated method stub
	    try {
	    	locDao.saveOrUpdate(loc);
	    }
	    catch (Exception ex) {
	      return "Error creating the location: " + ex.toString();
	    }
	    return "Location succesfully created!";
	}

	@Override
	public Location find(Location loc) {
		// TODO Auto-generated method stub
		return locDao.find(loc.getId());
	}

	@Override
	public Location find(Long id) {
		// TODO Auto-generated method stub
		return locDao.find(id);
	}

	@Override
	public Location find(String identity) {
		// TODO Auto-generated method stub
		return locDao.find(identity);
	}
	
	public Location findByNameLocation(String name, String city, String province) {
		// TODO Auto-generated method stub
		return locDao.find(name, city, province);
	}

	@Override
	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return locDao.getAll();
	}

	@Override
	public List<Location> findByCriteria(String c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Location loc) {
		locDao.saveOrUpdate(loc);
		
	}

	@Override
	public void delete(Location loc) {
		locDao.delete(loc);
	}

}
