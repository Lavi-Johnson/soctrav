package com.yanni.sotrav.services.User;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;

public class BaseUserService {
	
	@Autowired
	@Qualifier("userManagerBean")
	protected UserManager _userManager;
	
	public User find(long id){
		return _userManager.find(id);
	}
	
	public User find(String email){
		return _userManager.find(email);
	}
	
	public void Update(User usr){
		_userManager.update(usr);
	}
	
	public void delete(User usr){
		_userManager.delete(usr);
	}

}
