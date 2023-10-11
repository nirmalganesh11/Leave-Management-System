package lms.server.serverserviceinterfaces;

import lms.shared.User;


public interface UserService {
	
	User authenticate(String username,String password);
	
	String saveUser(User saved);
	
	User getUser(String username);
}
