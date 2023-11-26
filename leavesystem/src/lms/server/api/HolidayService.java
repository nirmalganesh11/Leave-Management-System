package lms.server.api;

import java.util.Date;
import java.util.List;

import lms.shared.utility.Holiday;

public interface HolidayService {
	
	String saveHoliday(Holiday he);
	
	List<Holiday> getAllHolidays();
	
	List<Date> listHolidayDates();
}
