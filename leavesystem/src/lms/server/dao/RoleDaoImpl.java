package lms.server.dao;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.security.Role;

public class RoleDaoImpl  extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);
	
	public RoleDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	@SuppressWarnings({"deprecation","unchecked"})
	public Role getRole(int roleId) {
	    try (Session session = factory.openSession()) {
	        List<Role> roles = (List<Role>) template.execute(new HibernateCallback<Object>() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(Role.class);
	                criteria.add(Restrictions.eq("roleId", roleId));
	                criteria.setFetchMode("permissions", FetchMode.JOIN);

	                criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	                return criteria.list();
	            }
	        });

	        return roles.isEmpty() ? null : roles.get(0);
	        
	    } catch (Exception e) {
	    	logger.error("Caught Exception in RoleDao Impl"+e.toString());
	        return null;
	    }
	}

	
	@SuppressWarnings({"deprecation","unchecked"})
	public List<Role> getAllRolesWithPermissions(){
		try {
		    List<Role> roleList = (List<Role>) template.execute(new HibernateCallback<Object>() {
		       
				@Override
		        public Object doInHibernate(Session session) throws HibernateException {
		            Criteria criteria = session.createCriteria(Role.class)
		                .setFetchMode("permissions", FetchMode.JOIN);
		            List<Role> list = criteria.list();
		            return list;
		        }
		    });
		    return roleList;
		}catch(Exception e) {
			logger.error("Caught Exception in RoleDao Impl"+e.toString());
			return Collections.emptyList();
		}
	}
	
}
