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
import com.yanni.sotrav.models.Location;
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
		IWebService createMsgService=(IWebService) factorybean.getBean("createMessageService");
		Message msg=(Message)createMsgService.process(request);
		return msg;
	}

}
