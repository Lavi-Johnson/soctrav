package com.yanni.sotrav.dao;

import java.util.ArrayList;
import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

public interface IMessageDao extends GenericDao<Message, Long>{
	  @SuppressWarnings("unchecked")
	  public List<Message> getAll();
	  public ArrayList<Message> getMsgsByMsg(String msg);
}
