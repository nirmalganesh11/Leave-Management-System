package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.utility.Holiday;

public interface HolidayService {
	
	String saveHoliday(Holiday he);
	
	List<Holiday> getAllHolidays();
	
	
}