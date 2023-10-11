package lms.server.serverservices;


import java.util.List;

import lms.server.daopack.CompanyDao;
import lms.server.daopack.DepartmentDao;

import lms.server.serverserviceinterfaces.HeirarchyService;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

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


	@Override
	public String saveDepartment(Department dept) {
		return deptDao.saveDepartment(dept);
	}


	@Override
	public List<Department> getAllDepartments() {
		return deptDao.getAllDepartments();
	}


	@Override
	public List<Department> companyDepartments(Company company) {
		int companyId = company.getCompanyId();
		return deptDao.companyDepartments(companyId);
	}
	
}
