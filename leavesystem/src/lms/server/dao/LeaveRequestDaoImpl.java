package lms.server.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.server.service.LeaveRequestServiceImpl;
import lms.shared.utility.LeaveRequest;

public class LeaveRequestDaoImpl extends CommonCode{

	public SessionFactory factory;
	private HibernateTemplate template;
	
	  private static final Logger logger = LogManager.getLogger(LeaveRequestDaoImpl.class);
	
	
	public LeaveRequestDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveLeaveRequest(LeaveRequest lro) {
		
		logger.info("inside the dao class");
		return saveEntity(lro,factory);
	}
	

	@SuppressWarnings({"deprecation","unchecked"})
	public List<LeaveRequest> getAllLeaveRequests(){
		try {
		List<LeaveRequest> requestList = (List<LeaveRequest>) template.execute(new HibernateCallback<Object>() {

	        @Override
	        public Object doInHibernate(Session session) throws HibernateException {
	        	
	            Criteria criteria = session.createCriteria(LeaveRequest.class);
	            criteria.setFetchMode("type",FetchMode.JOIN);
	            criteria.createAlias("leaveStatus","l",JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("employee","e",JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("e.company","c",JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("e.department","d",JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("e.role","r",JoinType.LEFT_OUTER_JOIN);
	            criteria.createAlias("r.permissions","p",JoinType.LEFT_OUTER_JOIN);
	            
	            
	            List<LeaveRequest> list = criteria.list();
	            
	            return list;
	        }
	    });
		
	    return requestList;
		}catch(Exception e) {
			logger.error("failed to retrieve list"+e.toString());
		}	
		return null;
	    
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
	
	@SuppressWarnings({"deprecation","unchecked"})
	public List<LeaveRequest> getLeaveRequestsByUserId(int userId){
		
		 try {
		        List<LeaveRequest> requestList = (List<LeaveRequest>) template.execute(new HibernateCallback<Object>() {

		            @Override
		            public Object doInHibernate(Session session) throws HibernateException {

		                Criteria criteria = session.createCriteria(LeaveRequest.class);
		                criteria.setFetchMode("type", FetchMode.JOIN);
		                criteria.setFetchMode("leaveStatus",FetchMode.JOIN);
		                criteria.createAlias("employee", "e", JoinType.LEFT_OUTER_JOIN);
		                criteria.createAlias("e.company", "c", JoinType.LEFT_OUTER_JOIN);
		                criteria.createAlias("e.department", "d", JoinType.LEFT_OUTER_JOIN);
		                criteria.createAlias("e.role", "r", JoinType.LEFT_OUTER_JOIN);
		                criteria.createAlias("r.permissions", "p", JoinType.LEFT_OUTER_JOIN);

		                criteria.add(Restrictions.eq("employee.userId", userId));

		                List<LeaveRequest> list = criteria.list();

		                return list;
		            }
		        });

		        return requestList;
		    } catch (Exception e) {
		        logger.error("Failed to retrieve leave requests by user ID: " + userId, e);
		    }
		    return null;
		
	}
	@SuppressWarnings({"deprecation","unchecked"})
	public LeaveRequest getLeaveRequestByRequestId(int requestId) {
		try {
	        List<LeaveRequest> leaveRequestList = template.execute(new HibernateCallback<List<LeaveRequest>>() {
	            @Override
	            public List<LeaveRequest> doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(LeaveRequest.class);
	                criteria.setFetchMode("type", FetchMode.JOIN);
	                criteria.setFetchMode("leaveStatus", FetchMode.JOIN);
	                criteria.createAlias("employee", "e", JoinType.LEFT_OUTER_JOIN);
	                criteria.createAlias("e.company", "c", JoinType.LEFT_OUTER_JOIN);
	                criteria.createAlias("e.department", "d", JoinType.LEFT_OUTER_JOIN);
	                criteria.createAlias("e.role", "r", JoinType.LEFT_OUTER_JOIN);
	                criteria.createAlias("r.permissions", "p", JoinType.LEFT_OUTER_JOIN);

	                criteria.add(Restrictions.eq("requestId", requestId));

	                return criteria.list();
	            }
	        });

	        if (leaveRequestList != null && !leaveRequestList.isEmpty()) {
	            LeaveRequest leaveRequest = leaveRequestList.get(0);
	            logger.info("Leave request retrieved successfully. Request ID: " + requestId);
	            return leaveRequest;
	        } else {
	            logger.warn("Leave request not found. Request ID: " + requestId);
	        }
	    } catch (Exception e) {
	        logger.error("Failed to retrieve leave request. Request ID: " + requestId, e);
	    }
	    return null;
	}
	
	public String updateLeaveRequest(LeaveRequest lr) {
		
		 try {
			 	logger.error(lr.getLeaveStatus().getName()+"--------------");
		        updateEntity(lr,factory);
		        logger.info("Leave request updated successfully. Request ID: " + lr.getRequestId());
		        return "Leave request updated successfully.";
		    } catch (Exception e) {
		        logger.error("Failed to update leave request. Request ID: " + lr.getRequestId(), e);
		        return "Failed to update leave request.";
		    }
		 
	}

}
