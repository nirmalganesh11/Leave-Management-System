package lms.server.daopack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.Holiday;

public class HolidayDao extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public HolidayDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public String saveHoliday(Holiday he) {
		
		return saveEntity(he,factory);
		
	}
	public List<Holiday> getAllHolidays(){
		
		String fetchClause= "";
		return getAllEntities(Holiday.class,factory,fetchClause);
	}
	
	public List<Date> listHolidayDates()
	{
		List<Holiday> holidays = getAllHolidays();
		List<Date> dates = new ArrayList<>();
		for(Holiday hol: holidays) {
			dates.add(hol.getHolidayDate());
		}
		return dates;
	}
}
