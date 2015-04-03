package com.yanni.sotrav.services.Message;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("createMessageService")
@Service
public class CreateMessageService implements IWebService{
	
	@Autowired
	@Qualifier("messageManagerBean")
	protected DataManager _msgManager;

	@Override
	public Object process(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		ServiceBeanMapper.mapBean(msg, request);
		return _msgManager.create(msg);
	}

}
