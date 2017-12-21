package com.yanni.sotrav.manager;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.yanni.sotrav.dao.UserDao;
import com.yanni.sotrav.dao.abstracts.IUserDao;
import com.yanni.sotrav.models.User;

@Component("userManagerBean")
public class UserManager implements DataManager<User, Long>{
	
	@Autowired
	@Qualifier("userDao") //need to declare the dao as a interface or else will get illegal argument exception
	private IUserDao _userDao;
	
	@Override
	public String create(User usr){
		_userDao.saveOrUpdate(usr);
	    return "User succesfully created!";//+ "new user is: "+newUser.toString()+" f_name: "+newUser.getFirst_name();
	}
	
	@Override
	public User find(Long id){
		return _userDao.find(id);
	}
	
	@Override
	public User find(String email){
		return _userDao.find(email);
	}
	
	@Override
	public List<User> findAll(){
		return _userDao.getAll();
	}
	
	@Override
	public void update(User usr){
		_userDao.saveOrUpdate(usr);
	}
	
	@Override
	public void delete(User usr){
		_userDao.delete(usr);
	}
	
	@Override
	public ArrayList<User> findByCriteria(Object name){
		return null;//_userDao.getUsersByName(name);
	}

	@Override
	public User find(User usr) {
		// TODO Auto-generated method stub
		return _userDao.getByEmail(usr.getUser_email());
	}

}
