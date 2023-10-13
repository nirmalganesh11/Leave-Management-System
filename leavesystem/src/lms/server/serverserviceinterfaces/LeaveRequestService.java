package lms.server.serverserviceinterfaces;

import java.util.Date;
import java.util.List;

import lms.shared.utility.LeaveRequest;

public interface LeaveRequestService {
	
	String saveLeaveRequest(LeaveRequest lro);
	
	List<LeaveRequest> getAllRequests();
	
	int countLeaveDaysDays(Date startDate,Date endDate,List<Date> holidayDates);
}
