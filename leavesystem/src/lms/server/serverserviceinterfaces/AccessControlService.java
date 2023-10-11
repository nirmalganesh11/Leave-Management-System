package lms.server.serverserviceinterfaces;

import java.util.List;

import lms.shared.security.Role;

public interface AccessControlService {

	Role getRole(int roleId);
	
	List<Role> getAllRoles();
}
