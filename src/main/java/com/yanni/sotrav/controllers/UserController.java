package com.yanni.sotrav.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yanni.sotrav.exceptions.ResourceNotFoundException;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;
import com.yanni.sotrav.services.User.BaseUserService;
import com.yanni.sotrav.common.ApplicationBeanFactory;
import com.yanni.sotrav.common.JsonConfigLoader;
/**
 * Class UserController
 */
@Controller
public class UserController {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  // Wire the UserDao that will be used inside this controller.
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);
	
	  @Autowired
	  ApplicationBeanFactory factorybean;
	  
	  @Autowired
	  @Qualifier("baseUserService")
	  private BaseUserService bus;
  
  // ===============
  // PRIVATE METHODS
  // ===============

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create/user")
  @ResponseBody
  public ModelAndView create(HttpServletRequest request){ // (@RequestHeader(value="email") String email, @RequestHeader(value="name") String name) {
	  IWebService webservice=(IWebService) factorybean.getBean("createUserService");
	  String processedMsg = (String) webservice.process(request);//userManager.setAndCreateUser(email, name);
	  ModelMap modelMap=new ModelMap();
	  modelMap.put("processedMsg", processedMsg);
	  return new ModelAndView("user", modelMap);
  }
  
  @RequestMapping(method=RequestMethod.GET, value="/find/user")//{id} //produces = { "application/json", "application/xml" } or you can headers = "Accept=application/json"
  public @ResponseBody User find(/*@PathVariable("id")*/ @RequestHeader(value="id") Long id) {
	User newUser=(User) bus.find(id);
    if (newUser==null){
    	throw new ResourceNotFoundException();
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
      User user=(User) bus.find(id);
      bus.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/getByEmail/user")
  @ResponseBody
  public String getByEmail(@RequestHeader(value="email") String email) {
    String userId;
    try {
      User user = bus.find(email);
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
  public String updateUser(@RequestHeader(value="id") long id, @RequestHeader(value="email") String email) {
	try {
      User user = (User) bus.find(id);
      user.setUser_email(email);
      System.out.println(user);
      LOGGER.info(email);
      bus.Update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  
  @RequestMapping(value="/find/users")
  @ResponseBody
  public List<User> findUsers(@RequestParam("name") String name) {
    LOGGER.info(name);
    List<User> users=null;
	try {
		users=bus.findByName(name);
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return users;
  }
  
	@RequestMapping("/send/user")
	public @ResponseBody User jsonParse(HttpServletRequest request) {
		User user=new User();
		try {
			user=JsonConfigLoader.load(request.getInputStream(), User.class);
			LOGGER.info("Debugg mofo!!! "+user.getUser_email());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
