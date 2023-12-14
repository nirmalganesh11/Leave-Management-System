package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.Employee;

@RemoteServiceRelativePath("emp")
public interface EmployeeServiceClient extends RemoteService {
	
	
	String saveEmployee(Employee emp);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployee(int userId);
	
	String deleteEmployee(int userId);
	
	List<Employee> getOnLeaveEmployees();
	

}
