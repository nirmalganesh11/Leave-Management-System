package lms.server.serverservices;

import java.util.List;

import lms.server.daopack.CompanyDao;
import lms.server.daopack.DepartmentDao;
import lms.server.daopack.PermissionDao;
import lms.server.daopack.RoleDao;
import lms.server.serverserviceinterfaces.AccessControlService;
import lms.shared.security.Permission;
import lms.shared.security.Role;

public class AccessControlServiceImpl implements AccessControlService{
	
	private RoleDao roleDao;
	
	private PermissionDao permDao;
	
	public AccessControlServiceImpl(RoleDao roleDao, PermissionDao permDao) {
		this.roleDao = roleDao;
		this.permDao = permDao;
	}	
	
	@Override
	public Role getRole(int roleId) {
		return roleDao.getRole(roleId);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

}
