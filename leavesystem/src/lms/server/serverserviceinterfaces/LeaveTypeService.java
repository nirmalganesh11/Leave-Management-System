package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.utility.LeaveType;

public interface LeaveTypeService {
	
	String saveLeaveType(LeaveType type);
	
	List<LeaveType> getAllLeaveTypes();
	

}
