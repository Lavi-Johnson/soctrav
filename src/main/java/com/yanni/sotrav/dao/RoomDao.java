package com.yanni.sotrav.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.dao.abstracts.GenericDaoImpl;
import com.yanni.sotrav.dao.abstracts.IRoomDao;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

@Repository("roomDao")
@Transactional
public class RoomDao extends GenericDaoImpl<Room, Long> implements IRoomDao{

	@Override
	public List<Room> getAll() {
	  return getEntityManager().createQuery("from Room").getResultList();
	}

	@Override
	public Room find(String identity) {
		// TODO Auto-generated method stub
		return null;
	}
}
