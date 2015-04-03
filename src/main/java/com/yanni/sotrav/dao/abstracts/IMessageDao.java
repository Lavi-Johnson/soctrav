package com.yanni.sotrav.dao.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.yanni.sotrav.models.Message;
import com.yanni.sotrav.models.User;

public interface IMessageDao extends GenericDao<Message, Long>{
	  public List<Message> getAll();
	  public ArrayList<Message> getMsgsByMsg(String msg);
}
