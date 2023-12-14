package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.utility.Message;

public interface MessageServiceClientAsync {
	
	void saveMessage(Message msg,AsyncCallback<String> callback);
	
	void getHrMessages(AsyncCallback<List<Message>> callback);
	
	void getStaffMessages(AsyncCallback<List<Message>> callback);
	

}
