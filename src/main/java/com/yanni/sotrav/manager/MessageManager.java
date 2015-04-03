package com.yanni.sotrav.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.MessageDao;
import com.yanni.sotrav.dao.abstracts.IMessageDao;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

@Component("messageManagerBean")
public class MessageManager implements DataManager<Message, Long>{
	
	@Autowired
	@Qualifier("messageDao") //need to declare the dao as a interface or else will get illegal argument exception
	private IMessageDao msgDao;
	
	@Override
	public String create(Message msg){
	    try {
	    	msgDao.saveOrUpdate(msg);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "Message succesfully created!";//+ "new user is: "+newUser.toString()+" f_name: "+newUser.getFirst_name();
	}
	
	@Override
	public Message find(Long id){
		return msgDao.find(id);
	}
	
	@Override
	public void update(Message msg){
		msgDao.saveOrUpdate(msg);
	}
	
	@Override
	public void delete(Message msg){
		msgDao.delete(msg);
	}

	@Override
	public Message find(Message obj) {
		// TODO Auto-generated method stub
		return msgDao.find(obj.getId());
	}

	@Override
	public List<Message> findByCriteria(String msg) {
		return msgDao.getMsgsByMsg(msg);
	}

	@Override
	public List<Message> findAll() {
		return msgDao.getAll();
	}

	@Override
	public Message find(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
