package com.yanni.sotrav.manager;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

public interface IRoomManager {
	public Room getFirstRoom(Long l, Long u);
}
