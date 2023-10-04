package lms.server.serverserviceinterfaces;

import lms.shared.User;
import lms.shared.security.Role;

public interface UserService {
	
	User authenticate(String username,String password);
	
	String saveUser(User saved);
	
	Role getRole();
	
	User getUser(String username);
	
}
