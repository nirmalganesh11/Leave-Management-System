package lms.server.serverservices;

import java.util.List;

import lms.server.daopack.EmployeeDao;
import lms.server.serverserviceinterfaces.EmployeeService;
import lms.shared.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao empdao;
	
	public EmployeeServiceImpl(EmployeeDao empdao) {
		this.empdao = empdao;
	}	
	

	@Override
	public String saveEmployee(Employee emp) {
		return empdao.saveEmployee(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empdao.getAllEmployees();
	}


	@Override
	public Employee getEmployee(int userId) {
		Employee newemp = empdao.getEmployee(userId);
		Employee emp = new Employee();
		
		emp.setUserId(newemp.getUserId());
		emp.setUsername(newemp.getUsername());
		emp.setFirstName(newemp.getFirstName());
		emp.setLastName(newemp.getLastName());
		emp.setPosition(newemp.getPosition());
		
		return	emp;
	}

}
