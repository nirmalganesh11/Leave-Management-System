package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

@RemoteServiceRelativePath("ladder")
public interface HeirarchyServiceClient extends RemoteService {
	
	String saveCompany(Company company);
	
	List<Company> getAllCompanies();
	
	
	String saveDepartment(Department dept);
	
	List<Department> getAllDepartments();
	
	List<Department> companyDepartments(Company company);
	
}
