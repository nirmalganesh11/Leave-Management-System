package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.utility.LeaveType;

@RemoteServiceRelativePath("leavetype")
public interface LeaveTypeServiceClient extends RemoteService{
	
	String saveLeaveType(LeaveType type);
	
	List<LeaveType> getAllLeaveTypes();
	
}
