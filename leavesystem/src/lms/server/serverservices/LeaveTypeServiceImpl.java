package lms.server.serverservices;

import java.util.List;

import lms.server.daopack.LeaveTypeDao;
import lms.server.serverserviceinterfaces.LeaveTypeService;
import lms.shared.utility.LeaveType;

public class LeaveTypeServiceImpl implements LeaveTypeService{
	
	private LeaveTypeDao dao;
	
	public LeaveTypeServiceImpl(LeaveTypeDao dao) {
		this.dao = dao;
	}
	

	@Override
	public String saveLeaveType(LeaveType type) {
		return dao.saveLeaveType(type);
	}

	@Override
	public List<LeaveType> getAllLeaveTypes() {
		return dao.getAllLeaveTypes();
	}

}
