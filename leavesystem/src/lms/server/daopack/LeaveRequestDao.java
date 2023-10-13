package lms.server.daopack;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		
		String fetchClause ="LEFT JOIN FETCH e.employee r LEFT JOIN FETCH r.company LEFT JOIN FETCH r.department LEFT JOIN FETCH r.role h LEFT JOIN FETCH h.permissions LEFT JOIN FETCH e.type";
		return getAllEntities(LeaveRequest.class,factory,fetchClause);
//		 try (Session session = factory.openSession()) {
//	            String jpql = "SELECT lr FROM LeaveRequest lr " +
//	                          "JOIN FETCH lr.employee " +
//	                          "JOIN FETCH lr.type";
//	            Query<LeaveRequest> query = session.createQuery(jpql, LeaveRequest.class);
//	            return query.getResultList();
//	        }
		
	}
	
	public int countLeaveDays(Date startDate, Date endDate, List<Date> holidayDates) {
		
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startDate);
	    int workingDays = 0;
	
	    while (!calendar.getTime().after(endDate)) {
	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	
	        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
	            
	            if (!holidayDates.contains(calendar.getTime())) {
	                workingDays++;
	            }
	        }
	        	
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	    }
	
	    return workingDays;
	}

}
