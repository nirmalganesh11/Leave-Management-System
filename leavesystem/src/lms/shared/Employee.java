package lms.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import lms.shared.heirarchy.Department;

public class Employee extends User implements IsSerializable{
	
	private int employeeId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Department department;
	
	
	public Employee() {
		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
