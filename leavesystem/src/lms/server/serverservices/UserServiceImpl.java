package lms.server.serverservices;

import org.hibernate.Hibernate;

import lms.server.daopack.UserDao;
import lms.server.serverserviceinterfaces.UserService;
import lms.shared.User;
import lms.shared.security.Role;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	

	@Override
	public User authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String saveUser(User saved) {
		return userDao.saveUser(saved);
	}


	@Override
	public Role getRole() {
		
		Role role = userDao.getRole();
		
		return role;
		
	}


	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
}
