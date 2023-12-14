package lms.server.servlet;

import java.util.Date;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.HolidayServiceClient;
import lms.server.api.HolidayService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.utility.Holiday;

public class HolidayServlet extends RemoteServiceServlet implements HolidayServiceClient{

	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private HolidayService holidayServ;
	
	
	
	public HolidayServlet(){
		context = ApplicationContextListener.appContext;
		holidayServ = context.getBean(HolidayService.class);
	}
	
	@Override
	public String saveHoliday(Holiday holiday) {
		return holidayServ.saveHoliday(holiday);
	}

	@Override
	public List<Holiday> getAllHolidays() {
		return holidayServ.getAllHolidays();
	}

	@Override
	public List<Date> listHolidayDates() {
		return holidayServ.listHolidayDates();
	}

}
