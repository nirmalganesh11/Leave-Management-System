package lms.shared.utility;

//import com.google.gwt.user.client.rpc.IsSerializable;

import lms.shared.framework.domain.PersistantObject;

public class LeaveType extends PersistantObject{
	
	private static final long serialVersionUID = 1L;

	private int leaveTypeId;
	
	private String leaveTypeName;
	
	private String leaveTypeDesc;
	
	private int daysCount;
	
	public LeaveType() {
		
	}
	public LeaveType(String leaveTypeName,String leaveTypeDesc,int daysCount) {
		this.leaveTypeName = leaveTypeName;
		this.leaveTypeDesc = leaveTypeDesc;
		this.daysCount = daysCount;
	}
	
	
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public String getLeaveTypeDesc() {
		return leaveTypeDesc;
	}
	public void setLeaveTypeDesc(String leaveTypeDesc) {
		this.leaveTypeDesc = leaveTypeDesc;
	}
	public int getDaysCount() {
		return daysCount;
	}
	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}
	
	
	@Override
	public void detach() {
		super.detach();
	}
	
}
