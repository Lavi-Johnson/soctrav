package com.yanni.sotrav.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yanni.sotrav.common.ApplicationBeanFactory;
import com.yanni.sotrav.common.JsonConfigLoader;
import com.yanni.sotrav.common.SharedConstants;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.services.BaseService;
import com.yanni.sotrav.services.IWebService;

/**
 * Class UserController
 */
@Controller
public class RoomController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MainController.class);
	
	@Autowired
	 ApplicationBeanFactory factorybean;
	
	@RequestMapping("create/chatroom")
	@ResponseBody
	public ModelAndView createChatRoom(HttpServletRequest request) {
		IWebService createRoomService=(IWebService) factorybean.getBean("roomService");
		IWebService createLocationService=(IWebService) factorybean.getBean("locationService");
		BaseService baseLocationService=(BaseService) factorybean.getBean("baseLocationService");
		String processedMsg="";
		Location loc=null;
		loc=(Location)createLocationService.process(request);
		try{
			request.setAttribute(SharedConstants.LOCATION_OBJECT, loc);
			processedMsg=(String) createRoomService.process(request);
		}catch(Exception e){
			LOGGER.error("deleting the location", e);
			baseLocationService.delete(loc);
		}
		ModelMap modelMap=new ModelMap();
		modelMap.put("processedMsg", processedMsg);
		return new ModelAndView("room", modelMap);
	}

}
