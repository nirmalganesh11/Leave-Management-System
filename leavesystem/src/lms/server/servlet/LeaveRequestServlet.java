package lms.server.servlet;

import java.util.Date;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.LeaveRequestServiceClient;
import lms.server.api.LeaveRequestService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.utility.LeaveRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LeaveRequestServlet extends RemoteServiceServlet implements LeaveRequestServiceClient{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LeaveRequestServlet.class);
	
	private ApplicationContext context;
	private LeaveRequestService reqServ;
	
	
	public LeaveRequestServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		reqServ = context.getBean(LeaveRequestService.class);
	}
	
	
	
	@Override
	public String saveLeaveRequest(LeaveRequest lro) {
		return reqServ.saveLeaveRequest(lro);
	}

	@Override
	public List<LeaveRequest> getAllLeaveRequests() {
		logger.debug("this is debug message");
		logger.info("This is an informational message.");
	    logger.warn("This is a warning message.");
	    logger.error("This is an error message.");

		return reqServ.getAllRequests();
	}



	@Override
	public int countLeaveDays(Date startDate, Date endDate, List<Date> holidayDates) {
		return reqServ.countLeaveDaysDays(startDate, endDate, holidayDates);
	}


}
