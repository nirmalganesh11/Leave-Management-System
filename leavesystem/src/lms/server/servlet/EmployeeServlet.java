package lms.server.servlet;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.asyncservices.EmployeeServiceClient;
import lms.server.api.EmployeeService;
import lms.server.api.LeaveRequestService;
import lms.server.framework.dao.ApplicationContextListener;
import lms.shared.Employee;
import lms.shared.utility.LeaveRequest;

public class EmployeeServlet extends RemoteServiceServlet implements EmployeeServiceClient {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(EmployeeServlet.class);
	
	private ApplicationContext context;
	private EmployeeService empServ;
	private LeaveRequestService reqServ;
	
	
	public EmployeeServlet(){
		context = ApplicationContextListener.appContext;
		empServ = context.getBean(EmployeeService.class);
		reqServ = context.getBean(LeaveRequestService.class);
	}
	
	
	@Override
	public String saveEmployee(Employee emp) {
		return empServ.saveEmployee(emp);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return empServ.getAllEmployees();
	}


	@Override
	public Employee getEmployee(int userId) {
		return empServ.getEmployee(userId);
	}


	@Override
	public String deleteEmployee(int userId) {
		return empServ.deleteEmployee(userId);
	}


	@Override
	public List<Employee> getOnLeaveEmployees() {
		
		List<Employee> totalOnLeaveList = new ArrayList<Employee>();
		
		List<LeaveRequest> currentOnLeaveRequests = reqServ.currentOnLeaveRequests();
		
		logger.error(currentOnLeaveRequests.toString());
		
		for(LeaveRequest lr: currentOnLeaveRequests) {
			totalOnLeaveList.add(lr.getEmployee());
		}
		
		return totalOnLeaveList;

	}
	
	

}
