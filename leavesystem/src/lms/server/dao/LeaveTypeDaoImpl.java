package lms.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.Holiday;
import lms.shared.utility.LeaveType;

public class LeaveTypeDaoImpl extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public LeaveTypeDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveLeaveType(LeaveType type) {
		
		return saveEntity(type,factory);
	}
	

	@SuppressWarnings({"deprecation","unchecked"})
	public List<LeaveType> getAllLeaveTypes(){
//		String fetchClause ="";
//		return getAllEntities(LeaveType.class,factory,fetchClause);
		List<LeaveType> typesList = (List<LeaveType>)template.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(LeaveType.class);
				List<Holiday> list = criteria.list();
				return list;
			}

		});
		
		return typesList;
	}
	
	
	
}
