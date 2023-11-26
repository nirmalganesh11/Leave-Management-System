package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.utility.LeaveType;

public interface LeaveTypeServiceClientAsync {
	
	void saveLeaveType(LeaveType type,AsyncCallback<String> callback);
	
	void getAllLeaveTypes(AsyncCallback<List<LeaveType>> callback);

}
