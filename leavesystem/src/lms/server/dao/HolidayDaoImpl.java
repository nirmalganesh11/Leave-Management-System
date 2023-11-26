package lms.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.utility.Holiday;

public class HolidayDaoImpl extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public HolidayDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		
		this.factory = sessionFactory;
	}
	
	
	public String saveHoliday(Holiday he) {
		
		
//		try {
//            template.save(he);
//            template.persist(he);
//            
//            return "saved";
//        } catch (Exception e) {
//            e.printStackTrace(); 
//            return "failed to save";
//        }
		
		return saveEntity(he,factory);
		
	}
	@SuppressWarnings({"deprecation","unchecked"})
	public List<Holiday> getAllHolidays(){
		
//		String fetchClause= "";
//		return getAllEntities(Holiday.class,factory,fetchClause);
		
		List<Holiday> holidayList = (List<Holiday>)template.execute(new HibernateCallback<Object>() {

			
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(Holiday.class);
				List<Holiday> list = criteria.list();
				return list;
			}

		});
		return holidayList;
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
