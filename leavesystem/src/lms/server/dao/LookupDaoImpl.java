package lms.server.dao;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.framework.domain.Category;
import lms.shared.framework.domain.Lookup;

public class LookupDaoImpl extends CommonCode {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	public static final Logger logger = LogManager.getLogger(LookupDaoImpl.class);
	
	public LookupDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		
		this.factory = sessionFactory;
	}
	
	public String saveCategory(Category category) {
		
		saveEntity(category,factory);
		return "saved";
	}
	
	@SuppressWarnings({"unchecked","deprecation"})
	public List<Lookup> getLookupsByCategoryId(long categoryId){
		 try {
		        return (List<Lookup>) template.execute(new HibernateCallback<Object>() {
		            @Override
		            public Object doInHibernate(Session session) throws HibernateException {
		                Criteria criteria = session.createCriteria(Lookup.class);
		                criteria.add(Restrictions.eq("category", categoryId));
		                return criteria.list();
		            }
		        });
		    } catch (Exception e) {
		        logger.error(e.toString() + " came from dao of lookup dao");
		    }
		    return Collections.emptyList();
		    
	}

}
