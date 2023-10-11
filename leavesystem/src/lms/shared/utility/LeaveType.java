package lms.shared.utility;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LeaveType implements IsSerializable{
	
	private int id;
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
