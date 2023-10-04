package lms.server.daopack;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.heirarchy.Company;


public class CompanyDao {
	
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public CompanyDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveCompany(Company company) {
//		template.save(company);
//		return "saved";
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();		 
		    session.save(company);
		    tx.commit();
		    
		} catch (Exception e) {
		    if (tx != null) tx.rollback();
		    e.printStackTrace();
		} finally {
		    session.close();
		}
		return "added";
	}
	
	
	public List<Company> getAllCompanies(){
		
		Session session = factory.openSession();
        List<Company> listOfCompanies = null;
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            List<Company> entitylist = session.createQuery("FROM lms.shared.heirarchy.Company").list();
            listOfCompanies = entitylist;
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            System.out.println("ERROR: " + e.getMessage());
        }
        finally{
            session.close();
            
        }
        return listOfCompanies;

	}
	

}
