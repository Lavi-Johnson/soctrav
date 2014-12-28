package com.yanni.sotrav.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component("applicationBeanFactory")
public class ApplicationBeanFactory {
	
	@Autowired
	private ApplicationContext appContext;
	
	public Object getBean(String beanname){
		return appContext.getBean(beanname);
	}

}
