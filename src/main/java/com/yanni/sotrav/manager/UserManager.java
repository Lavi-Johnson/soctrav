package com.yanni.sotrav.manager;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.IUserDao;
import com.yanni.sotrav.models.User;

@Component("userManagerBean")
public class UserManager {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao _userDao;
	
	public String createUser(User usr){
	    try {
			_userDao.saveOrUpdate(usr);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created!";//+ "new user is: "+newUser.toString()+" f_name: "+newUser.getFirst_name();
	}
	
	public User find(Long id){
		return _userDao.find(id);
	}
	
	public User find(String email){
		return _userDao.getByEmail(email);
	}
	
	public List<User> findAll(){
		return _userDao.getAll();
	}
	
	public void update(User usr){
		_userDao.saveOrUpdate(usr);
	}
	
	public void delete(User usr){
		_userDao.delete(usr);
	}

}
