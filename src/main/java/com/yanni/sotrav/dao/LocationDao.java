package com.yanni.sotrav.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.dao.abstracts.GenericDaoImpl;
import com.yanni.sotrav.dao.abstracts.ILocationDao;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

@Repository("locationDao")
@Transactional
public class LocationDao extends GenericDaoImpl<Location, Long> implements ILocationDao{

	@Override
	public List<Location> getAll() {
	  return getEntityManager().createQuery("from Location").getResultList();
	}
	
	public Location find(String name, String city, String province){
		return (Location) getEntityManager().createQuery(
		        "from Location where location_name =:name")
		        .setParameter("name", name)
		        .getSingleResult();
	}

	@Override
	public Location find(String provience) {
		// TODO Auto-generated method stub
		return null;
	}
}
