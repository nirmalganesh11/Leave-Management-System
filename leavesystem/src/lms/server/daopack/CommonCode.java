package lms.server.daopack;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class CommonCode {
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllEntities(Class<T> entityClass,SessionFactory factory,String fetchClause) {
	    Session session = factory.openSession();
	    List<T> listOfEntities = null;
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        List<T> entityList = session.createQuery("SELECT e FROM " + entityClass.getName() + " e " + fetchClause).list();
	        listOfEntities = entityList;
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null)
	            transaction.rollback();
	        System.out.println("ERROR: " + e.getMessage());
	    } finally {
	        session.close();
	    }
	    return listOfEntities;
	}
	
	
	public <T> String saveEntity(T entity,SessionFactory factory) {
	    Session session = factory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        session.save(entity);
	        tx.commit();
	        return "added bruvv";
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        return "error";
	    } finally {
	        session.close();
	    }
	   
	}


}
