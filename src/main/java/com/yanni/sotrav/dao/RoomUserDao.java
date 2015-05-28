package com.yanni.sotrav.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.dao.abstracts.GenericDaoImpl;
import com.yanni.sotrav.dao.abstracts.IRoomDao;
import com.yanni.sotrav.dao.abstracts.IRoomUserDao;
import com.yanni.sotrav.models.RoomUser;

@Repository("roomUserDao")
@Transactional
public class RoomUserDao extends GenericDaoImpl<RoomUser, Long> implements IRoomUserDao{

	@Override
	public RoomUser find(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
