package lms.server.daopack;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class DepartmentDao {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public DepartmentDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}

}
