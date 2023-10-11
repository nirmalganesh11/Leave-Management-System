package lms.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.LeaveTypeServiceClient;
import lms.server.serverserviceinterfaces.LeaveTypeService;
import lms.shared.utility.LeaveType;

public class LeaveTypeServlet extends RemoteServiceServlet implements LeaveTypeServiceClient {
	

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private LeaveTypeService typeServ;
	
	
	public LeaveTypeServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		typeServ = context.getBean(LeaveTypeService.class);
	}
	
	

	@Override
	public String saveLeaveType(LeaveType type) {
		return typeServ.saveLeaveType(type);
	}

	@Override
	public List<LeaveType> getAllLeaveTypes() {
		return typeServ.getAllLeaveTypes();
	}

}
