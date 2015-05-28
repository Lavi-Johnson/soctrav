package com.yanni.sotrav.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanni.sotrav.common.SharedConstants;
import com.yanni.sotrav.models.User;

/**
 * Class UserController
 */
@Controller
public class AdminNavController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminNavController.class);
	
	@RequestMapping("admin/room.html")
	public ModelAndView adminRoom(HttpServletRequest request) {
		//LOGGER.debug("Received request to get room list view");
		ModelMap modelMap=new ModelMap();
		User usr=(User)request.getSession().getAttribute("userLogin");
		modelMap.put("usrName", usr.getUser_email());
		return new ModelAndView("room", modelMap);
	}
	
	@RequestMapping("admin/user.html")
	public ModelAndView adminUser(HttpServletRequest request) {
		LOGGER.info("is this a admin???"+request.isUserInRole("ROLE_"+SharedConstants.ADMIN.toString()));
		ModelMap modelMap=new ModelMap();
		User usr=(User)request.getSession().getAttribute("userLogin");
		modelMap.put("usrName", usr.getUser_email());
		return new ModelAndView("user", modelMap);
	}
	
	@RequestMapping("nav/forbidden.html")
	public ModelAndView forbidden(HttpServletRequest request) {
		return new ModelAndView("forbidden");
	}

}