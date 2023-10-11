package lms.server.daopack;


import java.util.ArrayList;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.Employee;


public class EmployeeDao extends CommonCode {
	
	private SessionFactory factory;
	private HibernateTemplate template;
	
	
	public EmployeeDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	public String saveEmployee(Employee emp) {
		
		return saveEntity(emp,factory);

	}
	
	public List<Employee> getAllEmployees(){
		
		String fetchClause ="LEFT JOIN FETCH e.company LEFT JOIN FETCH e.department LEFT JOIN FETCH e.role r LEFT JOIN FETCH r.permissions";
		
		List<Employee> newList = new ArrayList<Employee>();
		List<Employee> oldList = new ArrayList<Employee>();
		oldList = getAllEntities(Employee.class,factory,fetchClause);
		for(Employee emp:oldList) {
			Employee newEmp = new Employee();
			newEmp.setPosition(emp.getPosition());
			newEmp.setFirstName(emp.getFirstName());
			newEmp.setLastName(emp.getLastName());
			newEmp.setEmail(emp.getEmail());
			newEmp.setUserId(emp.getUserId());
			newEmp.setUsername(emp.getUsername());
			newEmp.setPassword(emp.getPassword());
			newEmp.setDepartment(emp.getDepartment());
			
			
			newList.add(newEmp);
		}
		return newList;
	}
	
	public Employee getEmployee(int userId) {
		
		 Session session = factory.openSession();
		    try {
		        session.beginTransaction();
		        String jpql = "SELECT e FROM Employee e " +
	                      "LEFT JOIN FETCH e.company " +
	                      "LEFT JOIN FETCH e.department " +
	                      "LEFT JOIN FETCH e.role r " +
	                      "LEFT JOIN FETCH r.permissions " +  // Add a space here
	                      "WHERE e.userId = :userId";

		        TypedQuery<Employee> query = session.createQuery(jpql, Employee.class);
		        query.setParameter("userId", userId);

		        Employee employee = query.getSingleResult();

		        session.getTransaction().commit();

		        return employee;
		    } catch (NoResultException e) {
		   
		        if (session.getTransaction() != null && session.getTransaction().isActive()) {
		            session.getTransaction().rollback();
		        }
		        e.printStackTrace();
		        return null;
		    } catch (Exception e) {
		       
		        if (session.getTransaction() != null && session.getTransaction().isActive()) {
		            session.getTransaction().rollback();
		        }
		        e.printStackTrace();
		        return null;
		    }
	}


}
