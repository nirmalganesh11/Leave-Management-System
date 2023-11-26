package lms.server.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate5.HibernateTemplate;


import lms.shared.User;

import lms.shared.security.Permission;
import lms.shared.security.Role;


public class UserDaoImpl extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public UserDaoImpl(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}

	public String saveUser(User user) throws IOException
    {
		//saveEmployee();
		return saveEntity(user,factory);
    }
	

	public User getUser(String username) throws SQLException{
		
		Session session = factory.openSession();
		String jpql = "SELECT DISTINCT u FROM User u " +
                "JOIN FETCH u.role r " +
                "JOIN FETCH r.permissions " +
                "WHERE u.username = :username";
		TypedQuery<User> query = session.createQuery(jpql, User.class);
		query.setParameter("username", username);

		try {
			User user =  query.getSingleResult();
			
			return userParser(user);
			//return user;
			
		} catch (Exception e) {
			e.printStackTrace();
				return null;
		}
		
		
//		 try (Session session = factory.openSession()) {
//		        Criteria criteria = session.createCriteria(User.class);
//		        criteria.createAlias("role", "r", JoinType.INNER_JOIN);
//		        criteria.createAlias("r.permissions", "p", JoinType.INNER_JOIN);
//		        criteria.add(Restrictions.eq("username", username));
//
//		        // Set fetch mode for associations
//		        criteria.setFetchMode("role", FetchMode.JOIN);
//		        criteria.setFetchMode("role.permissions", FetchMode.JOIN);
//
//		        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//		        try {
//		            User user = (User) criteria.uniqueResult();
//		            return user;
//		        } catch (NoResultException e) {
//		            return null; // Handle the case where no user is found
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		        return null;
//		    }
		
		
	}
	
	
	
	
	public Role roleParser(Role role) {
		Role newRole = new Role();
		List<Permission> newList = new ArrayList<Permission>();
		for(Permission perm: role.getPermissions()) {
			newList.add(perm);
		}
		newRole.setPermissions(newList);
		newRole.setRoleId(role.getRoleId());
		newRole.setRoleName(role.getRoleName());
		return newRole;	
	}

	public User userParser(User user) {
		User newUser = new User();
		newUser.setUserId(user.getUserId());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setRole(roleParser(user.getRole()));
		return newUser;
	}
	

}
