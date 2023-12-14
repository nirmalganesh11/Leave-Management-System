package lms.server.api;

import java.util.Date;
import java.util.List;

import lms.shared.utility.LeaveRequest;

public interface LeaveRequestService {
	
	String saveLeaveRequest(LeaveRequest lro);
	
	List<LeaveRequest> getAllRequests();
	
	int countLeaveDaysDays(Date startDate,Date endDate,List<Date> holidayDates);
	
	List<LeaveRequest> getLeaveRequestsByUserId(int userId);
	
	LeaveRequest getLeaveRequestByRequestId(int requestId);
	
	String updateLeaveRequest(LeaveRequest lr);
	
	List<LeaveRequest> currentOnLeaveRequests();
	
	List<LeaveRequest> getAllRequestHistory();
	
	List<LeaveRequest> getAllPendingRequests();	
	
}
