package lms.server;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.HolidayServiceClient;
import lms.server.serverserviceinterfaces.HolidayService;
import lms.shared.utility.Holiday;

public class HolidayServlet extends RemoteServiceServlet implements HolidayServiceClient{

private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private HolidayService holidayServ;
	
	
	public HolidayServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
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
