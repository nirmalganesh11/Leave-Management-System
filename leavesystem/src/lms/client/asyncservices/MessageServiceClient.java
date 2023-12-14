package lms.client.asyncservices;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.utility.Message;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

@RemoteServiceRelativePath("message")
public interface MessageServiceClient extends RemoteService{
	
	String saveMessage(Message msg);
	
	List<Message> getHrMessages();
	
	List<Message> getStaffMessages();

}
