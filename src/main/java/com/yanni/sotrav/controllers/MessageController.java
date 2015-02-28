package com.yanni.sotrav.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yanni.sotrav.common.ApplicationBeanFactory;
import com.yanni.sotrav.common.JsonConfigLoader;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.IWebService;
import com.yanni.sotrav.services.Message.BaseMessageService;
import com.yanni.sotrav.services.User.BaseUserService;

@Controller
public class MessageController {

	// Wire the UserDao that will be used inside this controller.
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	ApplicationBeanFactory factorybean;
	
	@Autowired
	@Qualifier("baseMessageService")
	private BaseMessageService bms;

	@RequestMapping(value = "/send/msg")
	@ResponseBody
	public Message create(HttpServletRequest request) { 
		Message msg=new Message();
		try {
			msg=JsonConfigLoader.load(request.getInputStream(), Message.class);
			LOGGER.info("Debugg mofo!!! "+msg.getMessage());
			bms.create(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg=null;
		}
		return msg;
	}

}
