package com.yanni.sotrav.services.User;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.configs.security.UserRepository;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;

@Component("baseUserService")
@Service
public class BaseUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	
	@Autowired
	@Qualifier("userManagerBean")
	protected DataManager _userManager;
	
	public User find(Long id){
		return (User) _userManager.find(id);
	}
	
	public Object create(HttpServletRequest request) {
		User usr=new User();
		ServiceBeanMapper.mapBean(usr, request);
		return _userManager.create(usr);
	}
	
	public User find(String email){
		return (User) _userManager.find(email);
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

	public List<User> findByName(String name) {
		return _userManager.findByCriteria(name);
	}
	
	@Autowired
	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User usr=find(username);
		detailsChecker.check(usr);
		return usr;
	}

}
