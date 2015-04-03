package com.yanni.sotrav.dao.abstracts;

import java.util.List;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.User;

public interface ILocationDao extends GenericDao<Location, Long>{
	
	public List<Location> getAll();
	public Location find(String provience);
	public Location find(String name, String city, String province);

}
