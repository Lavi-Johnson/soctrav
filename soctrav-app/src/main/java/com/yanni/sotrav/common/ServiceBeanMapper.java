package com.yanni.sotrav.common;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yanni.sotrav.models.Location;
import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.Room;
import com.yanni.sotrav.models.User;

public class ServiceBeanMapper {
	
    public static final String SET = "set";
    	    
    public static final String GET = "get";
    
    private static final int MONTH=0;
    	    
    private static final int DAY=1;
   
    private static final int YEAR=2;
    
    private static final int ACTIVE=1;
    
    private static final int INACTIVE=2;
    
    private static final Integer USERTYPE=3;
	
	public static void mapBean(Object bean, HttpServletRequest request){
		Method[] declaredMethods =bean.getClass().getDeclaredMethods();
		List <Method> setterList=new ArrayList<Method>();
		for(Method method:declaredMethods){
			String metName=method.getName();
			String val="";
			String property="";
			Object convertedValue=null;
			if(metName.contains(SET)){
				setterList.add(method);
				property=metName.replaceFirst(SET, "").toLowerCase();
				val=request.getParameter(property);
			}
			try {
				if(val!=null && !val.equals(""))convertedValue=convertVal(val, method.getParameterTypes()[0]);
				if(val!=null && !val.equals("") && convertedValue!=null){
						method.invoke(bean, convertedValue);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setDefaultValues(bean);
	}
	
	private static void setDefaultValues(Object socTravObj){
		if(socTravObj instanceof Location){
			Location loc=(Location)socTravObj;
			if(loc.getJoined_dt()==null)loc.setJoined_dt(new Date());
			loc.setLast_updated(new Date());
			if(loc.getStatus()==null)loc.setStatus(ACTIVE);
		}else if(socTravObj instanceof Message){
			Message msg=(Message)socTravObj;
			if(msg.getMessage()==null)msg.setMessage("");
			msg.setUpdate_dt(new Date());
			if(msg.getUser_id()==null)msg.setUser_id(-1);
		}else if(socTravObj instanceof Room){
			Room room=(Room)socTravObj;
			room.setUpdate_dt(new Date());
		}else if(socTravObj instanceof User){
			User usr=(User)socTravObj;
			if(usr.getStatus()==null)usr.setStatus(ACTIVE);
			if(usr.getUser_type_id()==null)usr.setUser_type_id(USERTYPE);
			usr.setUpdated_dt(new Date());
			if(usr.getUser_registered()==null)usr.setUser_registered(new Date());
			if(usr.getUser_activation_key()==null)usr.setUser_activation_key(Calendar.getInstance().getTimeInMillis()+"_activation_key");
		}
	}
	
	private static Object convertVal(Object val, Object type) throws Exception{
		Object convertedValue=null;
      if(isBigDecimal(type)){
           convertedValue = convertToBigDecimal(val);
      }else if(isBoolean(type)){
           convertToBoolean(type);
      }else if(isCalendar(type)){
           convertedValue = convertToCalendar(val);
      }else if(isInteger(type)){
           convertedValue = convertToInteger(val);
      }else if(isString(type)){
           convertedValue = val;
      }else if(isDate(type)){
          convertedValue = convertToDate(val);
     }else{
           throw new Exception("this type:"+type+" is not supported currently in WebServiceBeanMapper");
      }
		return convertedValue;
	}
	
    private static boolean isBigDecimal(Object parameter){
        return (parameter == BigDecimal.class);
   }
  
   private static boolean isInteger(Object parameter){
        return (parameter == int.class || parameter == Integer.class);
   }
  
   private static boolean isString(Object parameter){
        return (parameter == String.class);
   }
   
   private static boolean isDate(Object parameter){
	   return (parameter == Date.class);
   }

   private static boolean isBoolean(Object parameter){
        return (parameter == Boolean.class);
   }

   private static boolean isCalendar(Object parameter){
        return (parameter == Calendar.class);
   }
  
   //need to change business logic for converting to date!
   private static Date convertToDate(Object parameter){
       Date date= new Date(parameter.toString());
	   return date;
  }

   private static String convertToString(Object parameter){
        return parameter.toString();
   }
  
   private static Boolean convertToBoolean(Object parameter){
        return Boolean.parseBoolean(parameter.toString());
   }
  
   private static Integer convertToInteger(Object parameter){
        return Integer.parseInt(parameter.toString());
   }
  
   private static BigDecimal convertToBigDecimal(Object parameter){
        return (new BigDecimal(parameter.toString()));
   }

   private static Calendar convertToCalendar(Object parameter){
        String [] date=parameter.toString().split("/");
        Calendar cal = getCalendar(Integer.parseInt(date[YEAR]), Integer.parseInt(date[MONTH]), Integer.parseInt(date[DAY]));
        return cal;
   }
   
   private static Calendar getCalendar(int year, int month, int day) {
       Calendar c = Calendar.getInstance();
       c.set(year, month-1, day);
       return c;
  }

}
