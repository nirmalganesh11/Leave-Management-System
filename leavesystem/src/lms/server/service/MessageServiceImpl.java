package lms.server.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import lms.server.api.MessageService;
import lms.server.dao.MessageDaoImpl;
import lms.server.dao.UserDaoImpl;
import lms.shared.utility.Message;

public class MessageServiceImpl implements MessageService {

	
	private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);

	private MessageDaoImpl msgDao;
	
	public MessageServiceImpl(MessageDaoImpl msgDao) {
		
	        this.msgDao = msgDao;
	}
	    
	@Override
	public String saveMessage(Message msg) {
		return msgDao.saveMessage(msg);
	}

	@Override
	public List<Message> getHrMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getStaffMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
