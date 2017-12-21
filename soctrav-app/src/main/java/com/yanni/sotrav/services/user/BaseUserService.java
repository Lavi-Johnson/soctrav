package com.yanni.sotrav.services.user;

import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.yanni.sotrav.common.ServiceBeanMapper;
import com.yanni.sotrav.manager.DataManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("baseUserService")
@Service
public class BaseUserService implements org.springframework.security.core.userdetails.UserDetailsService, BaseService{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BaseUserService.class);
	
	@Autowired
	@Qualifier("userManagerBean")
	protected DataManager _userManager;
	
	public Object create(HttpServletRequest request) {
		User usr=new User();
		ServiceBeanMapper.mapBean(usr, request);
		return _userManager.create(usr);
	}
	
	public User find(String email){
		return (User) _userManager.find(email);
	}
	
	public List<User> findAll(){
		return _userManager.findAll();
	}

	public List<User> findByName(String name) {
		return _userManager.findByCriteria(name);
	}
	
//	@Autowired
//	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User usr=find(username);
		detailsChecker.check(usr);
		return usr;
	}
	
//	public List<GrantedAuthority> getAuthorities(User user) {
//	    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//		if(user.getUser_type_id()== SharedConstants.SUPER_TYPE || user.getUser_type_id()==SharedConstants.ADMIN_TYPE){
//			authList.add(new SimpleGrantedAuthority(SharedConstants.ADMIN));
//			authList.add(new SimpleGrantedAuthority(SharedConstants.USER));
//		}
//		else{
//			authList.add(new SimpleGrantedAuthority(SharedConstants.USER));
//		}
//
//	    System.out.println(authList);
//	    return authList;
//	}

	@Override
	public Object find(Serializable id) {
		return _userManager.find((User)id);
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return _userManager.create((User)obj);
	}

	@Override
	public Object find(Object obj) {
		// TODO Auto-generated method stub
		User usr=(User) obj;
		return _userManager.create(usr);
	}

	@Override
	public void Update(Object usr) {
		_userManager.update((User)usr);
	}

	@Override
	public void delete(Object usr) {
		_userManager.delete((User)usr);
		
	}

	@Override
	public List findByCriteria(Object name) {
		// TODO Auto-generated method stub
		return null;
	}

}
