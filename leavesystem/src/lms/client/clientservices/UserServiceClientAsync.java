package lms.client.clientservices;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.User;
import lms.shared.security.Role;

public interface UserServiceClientAsync {
	
	void authenticate(String username,String password ,AsyncCallback<User>callback);
	
	void getLoggedInUser(AsyncCallback<String>callback);
	
	void saveUser(User user,AsyncCallback<String> callback);
	
	void getRole(AsyncCallback<Role> callback);
	
	void getUser(String username,AsyncCallback<User> callback);
	
	void isAuthenticated(AsyncCallback<Boolean> callback);
	
	
	void getAuthenticatedUser(AsyncCallback<User> callback);

}
