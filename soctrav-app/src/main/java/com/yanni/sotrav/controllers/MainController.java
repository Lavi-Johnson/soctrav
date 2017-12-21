package com.yanni.sotrav.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yanni.sotrav.common.ApplicationBeanFactory;
import com.yanni.sotrav.common.JsonConfigLoader;
import com.yanni.sotrav.common.SharedConstants;
import com.yanni.sotrav.dao.Dao;
import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.services.token.TokenAuthenticationService;
import com.yanni.sotrav.services.user.BaseUserService;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MainController.class);
	@Autowired
	private Dao dao;
	@Autowired
	ApplicationBeanFactory factorybean;

	// @Value("${application.message:Hello World}")
	// private String message = "Hello World";

	@RequestMapping("/returnbody")
	@ResponseBody
	public String readBody(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return stringBuilder.toString();
	}

	// http://localhost:8080/soctrav/
	@RequestMapping({ "/home.html", "/" })
	public String welcome(Map<String, Object> model) {
		LOGGER.debug("Received request to showview");
		model.put("time", new Date());
		model.put("message", "hello world");
		return "home";
	}

	@RequestMapping("/sendmessage")
	public @ResponseBody Message jsonParse(HttpServletRequest request) {
		InputStream inputStreamObject;
		Message msg = new Message();
		try {
			msg = JsonConfigLoader
					.load(request.getInputStream(), Message.class);
			// BufferedReader streamReader = new BufferedReader(new
			// InputStreamReader(inputStreamObject, "UTF-8"));
			// StringBuilder responseStrBuilder = new StringBuilder();
			//
			// String inputStr;
			// while ((inputStr = streamReader.readLine()) != null)
			// responseStrBuilder.append(inputStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

//	@RequestMapping(value = "/create/location")
//	@ResponseBody
//	public String createLocation() {
//		try {
//			Location location = new Location();
//			location.setCity("Atlanta");
//			location.setCountry("US");
//			location.setJoined_dt(new Date());
//			location.setLast_updated(new Date());
//			location.setLocation_name("Be Hostel");
//			location.setProvince_name("Ga");
//			location.setStatus(1);
//			location.setUnjoined_dt(null);
//			location.setZipcode("30324");
//			dao.saveOrUpdate(location);
//		} catch (Exception ex) {
//			return "Error creating the user: " + ex.toString();
//		}
//		return "User succesfully created!";
//	}

//	@RequestMapping(value = "/create/message")
//	@ResponseBody
//	public String createMsg() {
//		try {
//			Message msg = new Message();
//			msg.setMessage("Hello goodbye!");
//			msg.setRoom_id(1);
//			msg.setUpdate_dt(new Date());
//			msg.setUser_id(1);
//			dao.saveOrUpdate(msg);
//		} catch (Exception ex) {
//			return "Error creating the user: " + ex.toString();
//		}
//		return "User succesfully created!";
//	}

//	@RequestMapping(value = "/create/room")
//	@ResponseBody
//	public String createRoom() {
//		try {
//			Room room = new Room();
//			room.setLocation_id(1);
//			room.setRoom_name("nice_room");
//			room.setUpdate_dt(new Date());
//			dao.saveOrUpdate(room);
//		} catch (Exception ex) {
//			return "Error creating the user: " + ex.toString();
//		}
//		return "User succesfully created!";
//	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public String testing() {
		BaseUserService bus = (BaseUserService) factorybean
				.getBean("baseUserService");
		User usr = new User();
		try {
			usr = (User) bus.find((long) 1);
		} catch (Exception ex) {
			LOGGER.error("problem finding user", ex);
		}
		return "test success!!!" + usr.toString() + " " + usr.getFirst_name();
	}

	@RequestMapping("nav/chat.html")
	public ModelAndView getChatView() {
		ModelMap model = new ModelMap();
		return new ModelAndView("chat", model);
	}

	// @RequestMapping("/showview")
	// public String welcome(Map<String, Object> model) {
	// LOGGER.debug("Received request to showview");
	// model.put("time", new Date());
	// model.put("message", "hello world");
	// return "welcome";
	// }

	@RequestMapping("nav/showview.html")
	public ModelAndView getListUsersView() {
		LOGGER.debug("Received request to get user list view");
		ModelMap model = new ModelMap();
		model.put("time", new Date());
		model.put("message", "hello world");
		return new ModelAndView("welcome", model);
	}

	@RequestMapping("nav/admin.html")
	public ModelAndView admin() {
		LOGGER.debug("Received request to get user list view");
		return new ModelAndView("user");
	}

	@RequestMapping("/login.html")
	public ModelAndView signin() {
		LOGGER.debug("Received request to get user list view");
		ModelMap model = new ModelMap();
		model.put("time", new Date());
		model.put("message", "hello world");
		return new ModelAndView("signin", model);
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
//		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(
//				"X-AUTH-Cookie-Tok_" + System.currentTimeMillis(), "");
		TokenAuthenticationService.deleteCookie(request, response);
		request.getSession().invalidate();
		ModelMap model = new ModelMap();
		model.put("message", "logged out");
		return new ModelAndView("logout", model);
	}

	@RequestMapping("/forbidden.html")
	public ModelAndView forbidden(HttpServletRequest r) {
		LOGGER.debug("Received request to get user list view");
		ModelMap model = new ModelMap();
		model.put("url",r.getRequestURL().toString().replaceFirst("/forbidden.html", "/login.html"));
		return new ModelAndView("error", model);
	}

	// @RequestMapping("rest/getToken")
	// public @ResponseBody CsrfToken getToken(HttpServletRequest request) {
	// CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	// return token;
	// }

}
