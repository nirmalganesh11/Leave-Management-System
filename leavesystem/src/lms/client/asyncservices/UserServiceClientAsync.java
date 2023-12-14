package lms.client.asyncservices;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.User;
import lms.shared.security.Role;


public interface UserServiceClientAsync {
	
	void authenticate(String username,String password ,AsyncCallback<User>callback);
	
	void getLoggedInUser(AsyncCallback<String>callback);
	
	void saveUser(User user,AsyncCallback<String> callback);
	
	
	
	void getUser(String username,AsyncCallback<User> callback);
	
	void isAuthenticated(AsyncCallback<Boolean> callback);
	
	
	void getAuthenticatedUser(AsyncCallback<User> callback);
	
	void getAuthenticatedRole(AsyncCallback<Role> callback);
	
	void logoutUser(AsyncCallback<String> callback);

}
