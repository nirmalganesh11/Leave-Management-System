package lms.shared.heirarchy;

//import com.google.gwt.user.client.rpc.IsSerializable;

import lms.shared.framework.domain.PersistantObject;

public class Department extends PersistantObject{

	private static final long serialVersionUID = 1L;

	private int departmentId;
	
	private String departmentName;
	
	private String departmentDescription;
	
	private Company company;
	
	public Department() {
		
	}
	
	public Department(String name, String description ,Company company) {
		this.departmentName = name;
		this.departmentDescription = description;
		this.company = company;	
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public void detach(){
		super.detach();
		company.detach();
	}
}
