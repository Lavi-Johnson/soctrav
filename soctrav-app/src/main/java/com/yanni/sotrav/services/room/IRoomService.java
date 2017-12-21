package com.yanni.sotrav.services.room;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

public interface IRoomService {
	public Room getFirstRoom(Long l, Long u);
}
