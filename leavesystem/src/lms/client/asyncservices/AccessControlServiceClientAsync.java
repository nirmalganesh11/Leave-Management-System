package lms.client.asyncservices;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import lms.shared.security.Role;

public interface AccessControlServiceClientAsync {
	
	
	void getRole(int roleId,AsyncCallback<Role>callback);
	
	
	void getAllRoles(AsyncCallback<List<Role>> callback);


	

}
