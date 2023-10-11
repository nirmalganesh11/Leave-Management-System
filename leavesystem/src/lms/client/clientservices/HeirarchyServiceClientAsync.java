package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

public interface HeirarchyServiceClientAsync {
	
	void saveCompany(Company company , AsyncCallback<String> callback);
	
	void getAllCompanies(AsyncCallback<List<Company>> callback);
	
	void saveDepartment(Department dept,AsyncCallback<String>callback);
	
	void getAllDepartments(AsyncCallback<List<Department>> callback);
	
	
	void companyDepartments(Company company,AsyncCallback<List<Department>>callback);
	
	
	
}
