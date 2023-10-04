package lms.client.clientservices;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.User;
import lms.shared.security.Role;

@RemoteServiceRelativePath("auth")
public interface UserServiceClient extends RemoteService{

	User authenticate(String username,String password);
	
	String getLoggedInUser();
	
	String saveUser(User user);
	
	Role getRole();
	
	Boolean isAuthenticated();
	
	
	User getUser(String username);
	
	User getAuthenticatedUser();
}
