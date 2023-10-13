package lms.client.clientservices;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.utility.LeaveRequest;

@RemoteServiceRelativePath("request")
public interface LeaveRequestServiceClient extends RemoteService {
	
	String saveLeaveRequest(LeaveRequest lro);
	
	List<LeaveRequest> getAllLeaveRequests();
	
	int countLeaveDays(Date startDate, Date endDate, List<Date> holidayDates);
	
	//List<LeaveRequest> getUserRequests(int userId);
	
}
