package lms.server.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class PermissionDaoImpl extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public PermissionDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	
	
}
