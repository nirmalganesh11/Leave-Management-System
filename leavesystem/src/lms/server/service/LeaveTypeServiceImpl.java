package lms.server.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import lms.server.api.LeaveTypeService;
import lms.server.dao.LeaveTypeDaoImpl;
import lms.shared.utility.LeaveType;

public class LeaveTypeServiceImpl implements LeaveTypeService {

    private static final Logger logger = LogManager.getLogger(LeaveTypeServiceImpl.class);

    private LeaveTypeDaoImpl dao;

    public LeaveTypeServiceImpl(LeaveTypeDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public String saveLeaveType(LeaveType type) {
        try {
            return dao.saveLeaveType(type);
        } catch (DataAccessException e) {
            logger.error("Failed to save leave type: " + e.getMessage(), e);
            return "Failed to save leave type: " + e.getMessage();
        }
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        try {
            return dao.getAllLeaveTypes();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all leave types: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
