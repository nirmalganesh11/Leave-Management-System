package lms.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import lms.server.api.LeaveRequestService;
import lms.server.dao.LeaveRequestDaoImpl;
import lms.shared.framework.domain.Lookup;
import lms.shared.utility.LeaveRequest;

public class LeaveRequestServiceImpl implements LeaveRequestService {

    private static final Logger logger = LogManager.getLogger(LeaveRequestServiceImpl.class);

    private LeaveRequestDaoImpl dao;

    public LeaveRequestServiceImpl(LeaveRequestDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public String saveLeaveRequest(LeaveRequest lro) {
        try {
        	logger.info("inside the service class");
            return dao.saveLeaveRequest(lro);
        } catch (DataAccessException e) {
            logger.error("Failed to save leave request: " + e.getMessage(), e);
            return "Failed to save leave request: " + e.getMessage();
        }
    }

    @Override
    public List<LeaveRequest> getAllRequests() {
    	try {
    		 List<LeaveRequest> result = dao.getAllLeaveRequests();
    		 for(LeaveRequest lr: result) {
    			lr.detach();
    		 }
    		 return result;
    		
    	}catch(Exception e) {
    		logger.error("failed to retrieve all the data"+e.toString());
    		return Collections.emptyList();
    	}

    }

    @Override
    public int countLeaveDaysDays(Date startDate, Date endDate, List<Date> holidayDates) {
        try {
            return dao.countLeaveDays(startDate, endDate, holidayDates);
        } catch (DataAccessException e) {
            logger.error("Failed to count leave days: " + e.getMessage(), e);
            return -1; 
        }
    }

	@Override
	public List<LeaveRequest> getLeaveRequestsByUserId(int userId) {
		try {
   		 List<LeaveRequest> result = dao.getLeaveRequestsByUserId(userId);
   		 for(LeaveRequest lr: result) {
   			lr.detach();
   		 }
   		 return result;
   		
		}catch(Exception e) {
			logger.error("failed to retrieve all the data"+e.toString());
			return Collections.emptyList();
   		}
	}

	@Override
	public LeaveRequest getLeaveRequestByRequestId(int requestId) {
		try {
			LeaveRequest lr =  dao.getLeaveRequestByRequestId(requestId);
			lr.detach();
			return lr;
		}catch(Exception e) {
			logger.error("failed to get the leaveRequest by id"+e.toString());
			return null;
		}
	}

	@Override
	public String updateLeaveRequest(LeaveRequest lr) {
		try {
			return dao.updateLeaveRequest(lr);
			
		}catch(Exception e) {
			logger.error("failed update leave request"+e.toString());
			return null;
		}
	}

	public List<LeaveRequest> getAllPendingRequests() {
		
		List<LeaveRequest> allRequests = getAllRequests();
	    List<LeaveRequest> pendingRequests = new ArrayList<LeaveRequest>();

	    String pendingStatusName = "PENDING";

	    for (LeaveRequest request : allRequests) {
	        Lookup leaveStatus = request.getLeaveStatus();
	        if (leaveStatus != null && pendingStatusName.equals(leaveStatus.getName())) {
	            pendingRequests.add(request);
	        }
	    }

	    return pendingRequests;
	}
	
	
	public List<LeaveRequest> getAllRequestHistory(){
		
		List<LeaveRequest> allRequests = getAllRequests();
	    List<LeaveRequest> historyRequests = new ArrayList<LeaveRequest>();

	    String approvedStatusName = "APPROVED";
	    String rejectedStatusName = "REJECTED";

	    for (LeaveRequest request : allRequests) {
	        Lookup leaveStatus = request.getLeaveStatus();
	        
	        if(leaveStatus != null) {
	        	if((approvedStatusName.equals(leaveStatus.getName())) || (rejectedStatusName.equals(leaveStatus.getName()))) {
	        		historyRequests.add(request);
	        	}
	        	
	        }
	        
 
	    }

	    return historyRequests;

	}
	
	public List<LeaveRequest> getApprovedRequestHistory(){
		
		List<LeaveRequest> allRequests = getAllRequests();
	    List<LeaveRequest> historyRequests = new ArrayList<LeaveRequest>();

	    String approvedStatusName = "APPROVED";
	   
	    for (LeaveRequest request : allRequests) {
	        Lookup leaveStatus = request.getLeaveStatus();
	        
	        if(leaveStatus != null) {
	        	if(approvedStatusName.equals(leaveStatus.getName())) {
	        		historyRequests.add(request);
	        	}
	        	
	        }
	        
 
	    }

	    return historyRequests;

	}
	
	

	@Override
	public List<LeaveRequest> currentOnLeaveRequests() {
		
		List<LeaveRequest> approvedRequests = getApprovedRequestHistory();
		 List<LeaveRequest> currentOnLeave = new ArrayList<>();

	        Date today = new Date();
	        logger.error(today.toGMTString());

	        for (LeaveRequest request : approvedRequests) {
	            if (isDateBetween(request.getStartDate(), request.getEndDate(), today)) {
	                currentOnLeave.add(request);
	            }
	        }

	        return currentOnLeave;

	}
	
	private boolean isDateBetween(Date startDate, Date endDate, Date dateToCheck) {
	      
	        return !dateToCheck.before(startDate) && !dateToCheck.after(endDate);
	}
	
	
}
