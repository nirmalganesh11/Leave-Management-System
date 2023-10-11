package lms.server.daopack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import lms.shared.security.Permission;
import lms.shared.security.Role;

public class RoleDao  extends CommonCode{
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	
	public RoleDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	public Role getRole(int roleId) {
        
		try (Session session = factory.openSession()) {
		      
			String jpql = "SELECT DISTINCT r FROM Role r JOIN FETCH r.permissions WHERE r.roleId = :roleId";
            TypedQuery<Role> query = session.createQuery(jpql, Role.class);
            query.setParameter("roleId", roleId);
            Role role = query.getSingleResult();
            
            return roleParser(role);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
            
	}
	
	public List<Role> getAllRoles(){
		
		try (Session session = factory.openSession()) {
	        String jpql = "SELECT DISTINCT r FROM Role r JOIN FETCH r.permissions";
	        TypedQuery<Role> query = session.createQuery(jpql, Role.class);
	        List<Role> roles = query.getResultList();
	        
	        List<Role> parsedRoles = new ArrayList<>();
	        for (Role role : roles) {
	            parsedRoles.add(roleParser(role));
	        }
	        
	        return parsedRoles;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
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

}
