package lms.server.daopack;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class PermissionDao extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public PermissionDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	
	
}
