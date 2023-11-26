package lms.shared.utility;

import java.util.Date;

import lms.shared.Employee;
import lms.shared.framework.domain.Lookup;
import lms.shared.framework.domain.PersistantObject;

public class LeaveRequest extends PersistantObject {
	
	private static final long serialVersionUID = 1L;

	private int requestId;
	
	private Employee employee;
	
	private LeaveType type;
	
	private LeaveStatus status;
	
	private Lookup leaveStatus;
	
	private Date startDate;
	
	private Date endDate;
	
	private String description;
	
	private int countLeaves;
	
	
	
	public LeaveRequest() {
		
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getType() {
		return type;
	}

	public void setType(LeaveType type) {
		this.type = type;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCountLeaves() {
		return countLeaves;
	}

	public void setCountLeaves(int countLeaves) {
		this.countLeaves = countLeaves;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Lookup getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(Lookup leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	@Override
	public void detach() {
		super.detach();
		type.detach();
		employee.detach();
	}
	
}
