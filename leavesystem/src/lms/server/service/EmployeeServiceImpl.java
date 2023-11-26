package lms.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import lms.server.api.EmployeeService;
import lms.server.dao.EmployeeDaoImpl;
import lms.shared.Employee;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private EmployeeDaoImpl empdao;

    public EmployeeServiceImpl(EmployeeDaoImpl empdao) {
        this.empdao = empdao;
    }

    @Override
    public String saveEmployee(Employee emp) {
        try {
            return empdao.saveEmployee(emp);
        } catch (DuplicateKeyException e) {
            logger.error("Duplicate key violation while saving employee: " + e.getMessage(), e);
            return "Failed to save employee: Duplicate key violation";
        } catch (DataAccessException e) {
            logger.error("Failed to save employee: " + e.getMessage(), e);
            return "Failed to save employee: " + e.getMessage();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            ArrayList<Employee> emplist  = (ArrayList<Employee>) empdao.getAllEmployees();
            for(Employee emp: emplist) {
            	emp.detach();
            }
            return emplist;
            
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all employees: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public Employee getEmployee(int userId) {
    	
        try {
            Employee newemp = empdao.getEmployee(userId);
            Employee emp = new Employee();

            emp.setUserId(newemp.getUserId());
            emp.setUsername(newemp.getUsername());
            emp.setFirstName(newemp.getFirstName());
            emp.setLastName(newemp.getLastName());
            emp.setPosition(newemp.getPosition());

            return emp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("No employee found with userId " + userId + ": " + e.getMessage(), e);
            return new Employee();
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.error("Incorrect result size when retrieving employee with userId " + userId + ": " + e.getMessage(), e);
            return new Employee();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve employee with userId " + userId + ": " + e.getMessage(), e);
            return new Employee();
        }
    }
}
