package com.yanni.sotrav.services.location;

import java.util.List;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.User;

public interface ILocationService {
	public List<Location> getUserLocations(User usr);
}
