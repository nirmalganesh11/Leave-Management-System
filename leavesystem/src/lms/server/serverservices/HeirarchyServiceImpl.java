package lms.server.serverservices;


import java.util.List;

import lms.server.daopack.CompanyDao;
import lms.server.daopack.DepartmentDao;
import lms.server.daopack.UserDao;
import lms.server.serverserviceinterfaces.HeirarchyService;
import lms.shared.heirarchy.Company;

public class HeirarchyServiceImpl implements HeirarchyService {

	private CompanyDao companyDao;
	
	private DepartmentDao deptDao;
	
	public HeirarchyServiceImpl(CompanyDao companyDao, DepartmentDao deptDao) {
		this.companyDao = companyDao;
		this.deptDao = deptDao;
	}	
	
	
	@Override
	public String saveCompany(Company company) {
		return companyDao.saveCompany(company);
	}


	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAllCompanies();
	}
	
	

}
