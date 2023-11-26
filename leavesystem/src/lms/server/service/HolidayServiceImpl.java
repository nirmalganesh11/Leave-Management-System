package lms.server.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import lms.server.api.HolidayService;
import lms.server.dao.HolidayDaoImpl;
import lms.server.framework.dao.TemplateRootDaoImpl;
import lms.shared.utility.Holiday;

public class HolidayServiceImpl implements HolidayService {

	private HolidayDaoImpl dao;
	
	private TemplateRootDaoImpl rootdao;
	
	 private static final Logger logger = LogManager.getLogger(HolidayServiceImpl.class);
	 
	public HolidayServiceImpl(HolidayDaoImpl dao) {
		this.dao = dao;
	}
	
	  @Override
	    public String saveHoliday(Holiday holiday) {
	        try {
	            return dao.saveHoliday(holiday);
	     
	            // return rootdao.saveObjectType(holiday);
	        } catch (DataAccessException e) {
	            logger.error("Failed to save holiday: " + e.getMessage(), e);
	            return "Failed to save holiday: " + e.getMessage();
	        }
	    }

	    @Override
	    public List<Holiday> getAllHolidays() {
	        try {
	            return dao.getAllHolidays();
	        } catch (DataAccessException e) {
	            logger.error("Failed to retrieve all holidays: " + e.getMessage(), e);
	            return Collections.emptyList();
	        }
	    }

	    @Override
	    public List<Date> listHolidayDates() {
	        try {
	            return dao.listHolidayDates();
	        } catch (DataAccessException e) {
	            logger.error("Failed to retrieve holiday dates: " + e.getMessage(), e);
	            return Collections.emptyList();
	        }
	    }
	
	public HolidayDaoImpl getDao() {
		return dao;
	}

	public void setDao(HolidayDaoImpl dao) {
		this.dao = dao;
	}

	public TemplateRootDaoImpl getRootdao() {
		return rootdao;
	}
	
	public void setRootdao(TemplateRootDaoImpl rootdao) {
		this.rootdao = rootdao;
	}

}
