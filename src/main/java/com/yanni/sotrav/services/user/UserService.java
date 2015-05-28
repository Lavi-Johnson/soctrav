package com.yanni.sotrav.services.user;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("userService")
public class UserService implements IWebService{
	
	@Autowired
	@Qualifier("userManagerBean")
	private DataManager _userManager;
	
	private static final Integer USRSTATUS=3;

	@Override
	public Object process(HttpServletRequest request) {
		User usr=new User();
		ServiceBeanMapper.mapBean(usr, request);
		return _userManager.create(usr);
	}

}
