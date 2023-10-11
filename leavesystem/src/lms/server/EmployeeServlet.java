package lms.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.EmployeeServiceClient;
import lms.server.serverserviceinterfaces.EmployeeService;

import lms.shared.Employee;

public class EmployeeServlet extends RemoteServiceServlet implements EmployeeServiceClient {

private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private EmployeeService empServ;
	
	
	public EmployeeServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		empServ = context.getBean(EmployeeService.class);
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
	
	

}
