package lms.server.api;

import java.util.List;

import lms.shared.Employee;

public interface EmployeeService {
	
	String saveEmployee(Employee emp);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployee(int userId);
	
}
