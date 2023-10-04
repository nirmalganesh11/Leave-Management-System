package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.heirarchy.Company;

public interface HeirarchyService {
	
	String saveCompany(Company company);
	
	List<Company> getAllCompanies();
	
}
