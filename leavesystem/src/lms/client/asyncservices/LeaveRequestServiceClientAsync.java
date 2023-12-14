package lms.client.asyncservices;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.utility.LeaveRequest;

public interface LeaveRequestServiceClientAsync {
	
	void saveLeaveRequest(LeaveRequest lro,AsyncCallback<String> callback);
	
	void getAllLeaveRequests(AsyncCallback<List<LeaveRequest>> callback);
	
	void countLeaveDays(Date startDate,Date endDate,List<Date> holidayDays,AsyncCallback<Integer> callback);
	
	void getRequestsByUserId(int userId,AsyncCallback<List<LeaveRequest>> callback);
	
	
	void changeRequestStatus(int requestId,String status, AsyncCallback<String> callback);
	
	void getAllPendingRequests(AsyncCallback<List<LeaveRequest>> callback);
	
	void getAllRequestHistory(AsyncCallback<List<LeaveRequest>> callback);
	
}
