package lms.server.daopack;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.LeaveType;

public class LeaveTypeDao extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public LeaveTypeDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveLeaveType(LeaveType type) {
		
		return saveEntity(type,factory);
	}
	
	public List<LeaveType> getAllLeaveTypes(){
		String fetchClause ="";
		return getAllEntities(LeaveType.class,factory,fetchClause);
	}
	
	
	
}
