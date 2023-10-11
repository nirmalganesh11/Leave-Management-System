package lms.server.daopack;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.LeaveRequest;

public class LeaveRequestDao extends CommonCode{

	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public LeaveRequestDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveLeaveRequest(LeaveRequest lro) {
		
		return saveEntity(lro,factory);
	}
	
	public List<LeaveRequest> getAllLeaveRequests(){
		
		String fetchClause ="";
		return getAllEntities(LeaveRequest.class,factory,fetchClause);
		
	}
	
	
}
