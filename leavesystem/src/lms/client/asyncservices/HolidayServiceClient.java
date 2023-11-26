package lms.client.asyncservices;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.utility.Holiday;

@RemoteServiceRelativePath("holiday")
public interface HolidayServiceClient extends RemoteService{

	String saveHoliday(Holiday holiday);
	
	List<Holiday> getAllHolidays();
	
	List<Date> listHolidayDates();
}
