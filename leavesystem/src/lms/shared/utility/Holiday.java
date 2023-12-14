package lms.shared.utility;

import java.util.Date;

//import com.google.gwt.user.client.rpc.IsSerializable;

import lms.shared.framework.domain.PersistantObject;

public class Holiday extends PersistantObject{
	
	private static final long serialVersionUID = 1L;

	private int holidayId;
	
	private Date holidayDate;
	
	private String holidayName;
	
	public Holiday() {
		
	}
	public Holiday(Date ob,String name) {
		
		this.holidayDate =ob;
		this.holidayName = name;
	}
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getHolidayName(){
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
}
