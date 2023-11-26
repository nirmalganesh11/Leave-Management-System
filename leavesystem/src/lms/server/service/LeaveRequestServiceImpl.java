package lms.server.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import lms.server.api.LeaveRequestService;
import lms.server.dao.LeaveRequestDaoImpl;
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
}
