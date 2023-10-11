package lms.server.serverservices;

import java.util.List;

import lms.server.daopack.LeaveRequestDao;
import lms.server.serverserviceinterfaces.LeaveRequestService;
import lms.shared.utility.LeaveRequest;

public class LeaveRequestServiceImpl implements LeaveRequestService{
	
	private LeaveRequestDao dao;
	
	public LeaveRequestServiceImpl(LeaveRequestDao dao) {
		this.dao = dao;
	}
	
	
	
	@Override
	public String saveLeaveRequest(LeaveRequest lro) {
		return dao.saveLeaveRequest(lro);
	}

	@Override
	public List<LeaveRequest> getAllRequests() {
		return null;
	}

}
