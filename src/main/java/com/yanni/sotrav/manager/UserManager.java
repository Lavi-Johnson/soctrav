package com.yanni.sotrav.manager;
import java.util.Date;

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
	
	public String setAndCreateUser(String email, String name){
		User newUser;
	    try {
			newUser= new User();
			newUser.setLast_updated(new Date());
			newUser.setUser_registered(new Date());
			newUser.setUser_activation_key("abcdadfafasfsdf");
			newUser.setUser_email(email);
			newUser.setAlias("ypeng");
			newUser.setUser_name("yanniPeng");
			newUser.setFirst_name(name);
			newUser.setLast_name("chang");
			newUser.setUser_pass("vocal123");
			newUser.setUser_type_id(new Integer(3));
			newUser.setUser_registered(new Date());
			newUser.setUser_status(1);
			_userDao.saveOrUpdate(newUser);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created! and ";//+ "new user is: "+newUser.toString()+" f_name: "+newUser.getFirst_name();
	}

}
