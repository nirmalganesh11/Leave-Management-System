package lms.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.LeaveRequestServiceClient;
import lms.server.serverserviceinterfaces.LeaveRequestService;
import lms.shared.utility.LeaveRequest;

public class LeaveRequestServlet extends RemoteServiceServlet implements LeaveRequestServiceClient{

	private static final long serialVersionUID = 1L;
	
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
		return reqServ.getAllRequests();
	}

}
