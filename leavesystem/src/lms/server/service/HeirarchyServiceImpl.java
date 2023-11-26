package lms.server.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import lms.server.api.HeirarchyService;
import lms.server.dao.CompanyDaoImpl;
import lms.server.dao.DepartmentDaoImpl;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;

public class HeirarchyServiceImpl implements HeirarchyService {

    private CompanyDaoImpl companyDao;
    private DepartmentDaoImpl deptDao;
    private static final Logger logger = LogManager.getLogger(HeirarchyServiceImpl.class);

    public HeirarchyServiceImpl(CompanyDaoImpl companyDao, DepartmentDaoImpl deptDao) {
        this.companyDao = companyDao;
        this.deptDao = deptDao;
    }

    @Override
    public String saveCompany(Company company) {
        try {
            return companyDao.saveCompany(company);
        } catch (DataAccessException e) {
            logger.error("Failed to save company: " + e.getMessage(), e);
            return "Failed to save company: " + e.getMessage();
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        try {
            return companyDao.getAllCompanies();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all companies: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public String saveDepartment(Department dept) {
        try {
            return deptDao.saveDepartment(dept);
        } catch (DataAccessException e) {
            logger.error("Failed to save department: " + e.getMessage(), e);
            return "Failed to save department: " + e.getMessage();
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        try {
            return deptDao.getAllDepartments();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all departments: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Department> companyDepartments(Company company) {
        try {
            int companyId = company.getCompanyId();
            return deptDao.getDepartmentsByCompany(companyId);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve departments for company: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
