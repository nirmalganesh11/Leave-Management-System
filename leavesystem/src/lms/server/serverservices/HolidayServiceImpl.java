package lms.server.serverservices;


import java.util.Date;
import java.util.List;

import lms.server.daopack.HolidayDao;
import lms.server.serverserviceinterfaces.HolidayService;
import lms.shared.utility.Holiday;

public class HolidayServiceImpl implements HolidayService {

	private HolidayDao dao;
	
	public HolidayServiceImpl(HolidayDao dao) {
		this.dao = dao;
	}
	
	@Override
	public String saveHoliday(Holiday holiday) {
		return dao.saveHoliday(holiday);
	}

	@Override
	public List<Holiday> getAllHolidays() {
		return dao.getAllHolidays();
	}

	@Override
	public List<Date> listHolidayDates() {
		return dao.listHolidayDates();
	}

}
