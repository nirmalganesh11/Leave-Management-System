package lms.server.api;

import java.util.List;

import lms.shared.utility.Message;

public interface MessageService {
	
	String saveMessage(Message msg);
	
	List<Message> getHrMessages();
	
	List<Message> getStaffMessages();

}
