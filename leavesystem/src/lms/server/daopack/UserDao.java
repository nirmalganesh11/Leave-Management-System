package lms.server.daopack;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;


import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.orm.hibernate5.HibernateTemplate;


import lms.shared.User;

import lms.shared.security.Permission;
import lms.shared.security.Role;


public class UserDao extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public UserDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}

	public String saveUser(User user)
    {
		//saveEmployee();
		return saveEntity(user,factory);
    }
	

	public User getUser(String username) {
		
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
			
		} catch (Exception e) {
			e.printStackTrace();
				return null;
		}
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
