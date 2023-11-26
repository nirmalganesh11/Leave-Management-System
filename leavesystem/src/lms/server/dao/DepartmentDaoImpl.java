package lms.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;


import lms.shared.heirarchy.Department;

public class DepartmentDaoImpl extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public DepartmentDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	public String saveDepartment(Department department) {
		
		return saveEntity(department,factory);
	}
	
	public List<Department> getAllDepartments(){
		
	
		 String fetchClause = "JOIN FETCH e.company"; 
		    
		 return getAllEntities(Department.class, factory, fetchClause);
		
	}
	public List<Department> getDepartmentsByCompany(int companyId){
		
		
		Session session = factory.openSession();
	    Transaction transaction = null;
	    List<Department> departmentList = null;

	    try {
	        transaction = session.beginTransaction();

	        TypedQuery<Department> query = session.createQuery("SELECT d FROM Department d JOIN FETCH d.company WHERE d.company.companyId = :companyId", Department.class);
	        query.setParameter("companyId", companyId);

	        departmentList = query.getResultList();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        //e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return departmentList;
	    
	}
	

}
