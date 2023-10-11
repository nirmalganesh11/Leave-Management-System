package lms.client.clientservices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import lms.shared.security.Role;

@RemoteServiceRelativePath("access")
public interface AccessControlServiceClient extends RemoteService {
	
	Role getRole(int roleId);
	
	List<Role> getAllRoles();
}
