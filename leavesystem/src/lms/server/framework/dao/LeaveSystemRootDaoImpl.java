package lms.server.framework.dao;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public abstract class LeaveSystemRootDaoImpl extends SpringHibernateBaseRoot {
   
	public long create(Object entity) {
		return 0;
	}
	
	public void delete(int id) {
		delete(new Integer(id));
	}
	
	public void delete(Integer id) {
		delete(get(id));
	}
	
	public Object get(final Integer id) {
		return execute(new HibernateCallback<Object>() {

			@SuppressWarnings("unchecked")
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.enableFilter("filterdeleted");
				return get(getReferenceClass(),id);
			}

		});	
	}
	
	public Object saveObject(final Object entity) {
	    return execute(new HibernateCallback<Object>() {
	        @Override
	        public Object doInHibernate(Session session) throws HibernateException {
	            session.saveOrUpdate(entity);
	            //session.getTransaction().commit();
	           return entity;
	        }
	    });
		

		
	}
	
}
