package com.yanni.sotrav.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

@Repository("messageDao")
@Transactional
public class MessageDao extends GenericDaoImpl<Message, Long> implements IMessageDao{
	
	  /**
	   * Method getAll
	   * <br/>
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Message> getAll() {
	    return getEntityManager().createQuery("from Messages").setMaxResults(1000).getResultList();
	  }
	  
	  public ArrayList<Message> getMsgsByMsg(String msg) {
		    msg="%"+msg+"%";
		    System.out.println("the msg we are searching for is:"+msg);
		    return (ArrayList<Message>) getEntityManager().createQuery(
		        "from Messages where message like :msg") //CONCAT(first_name,' ',last_name)
		        .setParameter("msg", msg).setMaxResults(20)
		        .getResultList();
	  }

	@Override
	public Message find(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
