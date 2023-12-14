package lms.server.dao;


import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.Employee;


public class EmployeeDaoImpl extends CommonCode {
	
	private SessionFactory factory;
	private HibernateTemplate template;
	
	public static final Logger logger = LogManager.getLogger(EmployeeDaoImpl.class);
			
	
	public EmployeeDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	public String saveEmployee(Employee emp) {
		
		return saveEntity(emp,factory);

	}
	
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<Employee> getAllEmployees(){
		try {
		return (List<Employee>) template.execute(new HibernateCallback<Object>() {
	        @Override
	        public Object doInHibernate(Session session) throws HibernateException {
	            Criteria criteria = session.createCriteria(Employee.class);
	            criteria.setFetchMode("company", FetchMode.JOIN);
	            criteria.setFetchMode("department", FetchMode.JOIN);
	            criteria.createAlias("role", "r", JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("r.permissions","p", JoinType.LEFT_OUTER_JOIN);

	            List<Employee> oldList = criteria.list();
	            
	            return oldList;
	        }
	    });
	 }catch(Exception e) {
		 logger.info(e.toString()+"came from dao of employee dao");
	 }
	 return Collections.emptyList();
		
	}
	
	public Employee getEmployee(int userId) {
		
//		 Session session = factory.openSession();
//		    try {
//		        session.beginTransaction();
//		        String jpql = "SELECT e FROM Employee e " +
//	                      "LEFT JOIN FETCH e.company " +
//	                      "LEFT JOIN FETCH e.department " +
//	                      "LEFT JOIN FETCH e.role r " +
//	                      "LEFT JOIN FETCH r.permissions " +  // Add a space here
//	                      "WHERE e.userId = :userId";
//
//		        TypedQuery<Employee> query = session.createQuery(jpql, Employee.class);
//		        query.setParameter("userId", userId);
//
//		        Employee employee = query.getSingleResult();
//
//		        session.getTransaction().commit();
//
//		        return employee;
//		    } catch (NoResultException e) {
//		   
//		        if (session.getTransaction() != null && session.getTransaction().isActive()) {
//		            session.getTransaction().rollback();
//		        }
//		        e.printStackTrace();
//		        return null;
//		    } catch (Exception e) {
//		       
//		        if (session.getTransaction() != null && session.getTransaction().isActive()) {
//		            session.getTransaction().rollback();
//		        }
//		        e.printStackTrace();
//		        return null;
//		    }
		
		
		  return template.execute(new HibernateCallback<Employee>() {
	            @Override
	            public Employee doInHibernate(Session session) throws HibernateException {
	                @SuppressWarnings("deprecation")
					Criteria criteria = session.createCriteria(Employee.class);
	                criteria.setFetchMode("company", FetchMode.JOIN);
	                criteria.setFetchMode("department", FetchMode.JOIN);
	                criteria.createAlias("role", "r", org.hibernate.sql.JoinType.LEFT_OUTER_JOIN);
	                criteria.setFetchMode("r.permissions", FetchMode.JOIN);
	                criteria.add(Restrictions.eq("userId", userId));

	                return (Employee) criteria.uniqueResult();
	            }
	        });

	}
	
	public String deleteEmployee(int userId) {
		logger.info("added this");
		 String resultMessage;
	        try (Session session = factory.openSession()) {
	            Transaction transaction = session.beginTransaction();

	            try {
	                // Load the employee entity by its ID
	                Employee employeeToDelete = session.get(Employee.class, userId);

	                if (employeeToDelete != null) {
	                    // Delete the entity
	                    session.delete(employeeToDelete);
	                    resultMessage = "Employee deleted successfully.";
	                } else {
	                    resultMessage = "Employee with ID " + userId + " not found.";
	                }

	                transaction.commit();
	            } catch (Exception e) {
	                transaction.rollback();
	                resultMessage = "Error deleting employee: " + e.getMessage();
	            }
	        }
	        return resultMessage;
	    }
		
}



