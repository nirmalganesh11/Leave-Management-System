package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.utility.LeaveRequest;

public interface LeaveRequestService {
	
	String saveLeaveRequest(LeaveRequest lro);
	
	List<LeaveRequest> getAllRequests();
}
