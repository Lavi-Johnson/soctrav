package com.yanni.sotrav.services.location;

import java.util.Set;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.User;

public interface ILocationService {
	public Set<Location> getUserLocations(User usr);
}
