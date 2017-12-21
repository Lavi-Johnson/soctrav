package com.yanni.sotrav.services.message;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.MessageManager;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

@Component("baseMessageService")
@Service
public class BaseMessageService{
	
	@Autowired
	@Qualifier("messageManagerBean")
	protected DataManager msgManager;
	
	public Message find(Long id){
		return (Message) msgManager.find(id);
	}
	
	public String create(Message msg) {
		return msgManager.create(msg);
	}
	
	public void Update(Message msg){
		msgManager.update(msg);
	}
	
	public void delete(Message msg){
		msgManager.delete(msg);
	}

	public List<Message> findByMessage(String msg) {
		return msgManager.findByCriteria(msg);
	}

}
