package com.yanni.sotrav.services.User;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;

@Component("createUserService")
public class CreateUserService extends BaseUserService implements IWebService{

	@Override
	public Object process(Map<String, String> parmMap) {
		String email=parmMap.get("email");
		String name=parmMap.get("name");
		return _userManager.setAndCreateUser(email, name);
	}

}
