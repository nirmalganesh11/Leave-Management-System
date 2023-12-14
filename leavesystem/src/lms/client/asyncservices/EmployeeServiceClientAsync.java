package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.Employee;

public interface EmployeeServiceClientAsync {
	
	void saveEmployee(Employee emp,AsyncCallback<String>callback);

	void getAllEmployees(AsyncCallback<List<Employee>> callback);
	
	void getEmployee(int userId,AsyncCallback<Employee> callback);
	
	void deleteEmployee(int userId,AsyncCallback<String> callback);
	
	void getOnLeaveEmployees(AsyncCallback<List<Employee>> callback);

}