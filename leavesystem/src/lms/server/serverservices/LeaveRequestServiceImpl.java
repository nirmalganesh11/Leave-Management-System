package lms.server.serverservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lms.server.daopack.LeaveRequestDao;
import lms.server.serverserviceinterfaces.LeaveRequestService;
import lms.shared.Employee;
import lms.shared.utility.LeaveRequest;
import lms.shared.utility.LeaveType;

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
		List<LeaveRequest> result = dao.getAllLeaveRequests();
		List<LeaveRequest> answer = new ArrayList<>();
		for(LeaveRequest lr : result) {
			LeaveRequest newlr = new LeaveRequest();
			Employee emp = new Employee();
			LeaveType newtype = new LeaveType();
			
			emp.setEmployeeId(lr.getEmployee().getEmployeeId());
			emp.setFirstName(lr.getEmployee().getFirstName());
			newtype.setLeaveTypeId(lr.getType().getLeaveTypeId());
			newtype.setLeaveTypeName(lr.getType().getLeaveTypeName());
			
			newlr.setEmployee(emp);
			newlr.setType(newtype);
			
			newlr.setCountLeaves(lr.getCountLeaves());
			newlr.setDescription(lr.getDescription());
			newlr.setStartDate(lr.getStartDate());
			newlr.setEndDate(lr.getEndDate());
			newlr.setRequestId(lr.getRequestId());
			
			newlr.setStatus(lr.getStatus());
			
			answer.add(newlr);
			
		}	
		
		return answer;
	}



	@Override
	public int countLeaveDaysDays(Date startDate, Date endDate, List<Date> holidayDates) {
		return dao.countLeaveDays(startDate, endDate, holidayDates);
	}

}
