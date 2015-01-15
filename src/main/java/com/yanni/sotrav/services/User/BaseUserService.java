package com.yanni.sotrav.services.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;

@Component("baseUserService")
public class BaseUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	
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
	
	public List<User> findAll(){
		return _userManager.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return find(username);
	}

}
