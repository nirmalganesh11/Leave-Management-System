package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.utility.LeaveRequest;

public interface LeaveRequestServiceClientAsync {
	
	void saveLeaveRequest(LeaveRequest lro,AsyncCallback<String> callback);
	
	void getAllLeaveRequests(AsyncCallback<List<LeaveRequest>> callback);
	
}
