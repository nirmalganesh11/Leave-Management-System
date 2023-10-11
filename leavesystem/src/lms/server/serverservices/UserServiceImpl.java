package lms.server.serverservices;



import lms.server.daopack.UserDao;
import lms.server.serverserviceinterfaces.UserService;
import lms.shared.User;


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
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
}
