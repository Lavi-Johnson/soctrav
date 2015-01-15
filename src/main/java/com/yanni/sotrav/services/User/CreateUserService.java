package com.yanni.sotrav.services.User;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.dao.IUserDao;
import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("createUserService")
public class CreateUserService extends BaseUserService implements IWebService{
	
	@Autowired
	@Qualifier("userManagerBean")
	private UserManager _userManager;
	
	private static final Integer USRSTATUS=3;

	@Override
	public Object process(HttpServletRequest request) {
		User usr=new User();
		ServiceBeanMapper.mapBean(usr, request);
		return _userManager.createUser(usr);
	}

}
