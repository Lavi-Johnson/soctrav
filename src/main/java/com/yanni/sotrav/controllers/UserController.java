package com.yanni.sotrav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yanni.sotrav.exceptions.ResourceNotFoundException;
import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.dao.IUserDao;

/**
 * Class UserController
 */
@Controller
public class UserController {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  // Wire the UserDao that will be used inside this controller.
	  @Autowired
	  @Qualifier("userDao")
	  private IUserDao _userDao;
	  
	  @Autowired
	  @Qualifier("userManagerBean")
	  private UserManager _userManager;
  
  // ===============
  // PRIVATE METHODS
  // ===============

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create/user")
  @ResponseBody
  public String create(@RequestHeader(value="email") String email, @RequestHeader(value="name") String name) {
	  return _userManager.setAndCreateUser(email, name);
  }
  
  @RequestMapping(method=RequestMethod.GET, value="/find/user")//{id} //produces = { "application/json", "application/xml" } or you can headers = "Accept=application/json"
  public @ResponseBody User find(/*@PathVariable("id")*/ @RequestHeader(value="id") Long id) {
	User newUser= new User();
	newUser.setId(id);
	newUser=_userDao.find(id);
    String resp="user not found!!!";
    if (newUser==null){
    	throw new ResourceNotFoundException();
    }else{
    	resp="found the user: "+newUser.toString()+" f_name: "+newUser.getFirst_name()+" l_name"+newUser.getLast_name();
    }
    return newUser;
  }
    
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/delete/user")
  @ResponseBody
  public String delete(@RequestHeader(value="id") Long id) {
    try {
      User user=_userDao.find(id);
      _userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-email/user")
  @ResponseBody
  public String getByEmail(@RequestHeader(value="email") String email) {
    String userId;
    try {
      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/update/user")
  @ResponseBody
  //@RequestBody BookCase bookCase
  public String updateName(@RequestHeader(value="id") long id, @RequestHeader(value="email") String email) {
    try {
      User user = _userDao.find(id);
      user.setUser_email(email);
      _userDao.saveOrUpdate(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 

} // class UserController
