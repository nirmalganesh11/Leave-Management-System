package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

public interface HeirarchyService {
	
	String saveCompany(Company company);
	
	List<Company> getAllCompanies();
	
	String saveDepartment(Department dept);
	
	List<Department> getAllDepartments();
	
	List<Department> companyDepartments(Company company);
	
	
}
