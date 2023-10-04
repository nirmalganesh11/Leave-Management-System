package lms.server.daopack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.Employee;
import lms.shared.User;
import lms.shared.heirarchy.Company;
import lms.shared.heirarchy.Department;
import lms.shared.security.Permission;
import lms.shared.security.Role;


public class UserDao {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public UserDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}

	public String saveUser(User user)
    {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();		 
		    
		    session.save(user);

		    tx.commit();
		    
		} catch (Exception e) {
		    if (tx != null) tx.rollback();
		    e.printStackTrace();
		} finally {
		    session.close();
		}

		return "added";
    }
	
	
	public void saveEmployee() {
		
		Company newCompany = new Company("secondcompany","cmandescriptionon");
		
		Department dep = new Department();
		dep.setCompany(newCompany);
		dep.setDepartmentName("hement");
		dep.setDepartmentDescription("thn shit");
		
		Employee emp = new Employee();
		emp.setDepartment(dep);
		emp.setEmail("shit@gmail.com");
		emp.setFirstName("manualemployee");
		emp.setLastName("lastname");
		emp.setUsername("sampleusername");
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();		 
		   
		    session.save(emp);

		    tx.commit();
		} catch (Exception e) {
		    if (tx != null) tx.rollback();
		    e.printStackTrace();
		} finally {
		    session.close();
		}

		
		
		
		
	}
	
	public Role getRole() {
		         
		try (Session session = factory.openSession()) {
		      
			String jpql = "SELECT DISTINCT r FROM Role r JOIN FETCH r.permissions WHERE r.roleId = :roleId";
            TypedQuery<Role> query = session.createQuery(jpql, Role.class);
            query.setParameter("roleId", 39);
            Role role = query.getSingleResult();
            
            
            return roleParser(role);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
            
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
