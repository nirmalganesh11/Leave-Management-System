package lms.server.framework.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

public abstract class SpringHibernateBaseRoot extends HibernateTemplate {
	
	@SuppressWarnings("rawtypes")
	protected abstract Class getReferenceClass();
	
	@SuppressWarnings("deprecation")
	protected Criteria createCriteria(Session s) throws DataAccessException{
		return s.createCriteria(getReferenceClass());	
	}
	
	@SuppressWarnings("deprecation")
	public Criteria createCriteria() throws HibernateException{
		Session s = getSessionFactory().openSession();
		return s.createCriteria(getReferenceClass());
	}
	
	public String getDefaultOrderProperty() {
		return null;
	}
	
	
}
