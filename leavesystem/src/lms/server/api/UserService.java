package lms.server.api;

import java.io.IOException;
import java.sql.SQLException;

import lms.shared.User;


public interface UserService   {
	
	User authenticate(String username,String password);
	
	String saveUser(User saved) throws IOException;
	
	User getUser(String username) throws SQLException;
}
