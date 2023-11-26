package lms.server.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import lms.server.api.AccessControlService;
import lms.server.dao.PermissionDaoImpl;
import lms.server.dao.RoleDaoImpl;
import lms.shared.security.Permission;
import lms.shared.security.Role;

@Service
public class AccessControlServiceImpl implements AccessControlService {

    private static final Logger logger = LogManager.getLogger(AccessControlServiceImpl.class);

    private final RoleDaoImpl roleDao;
    private final PermissionDaoImpl permDao;

    public AccessControlServiceImpl(RoleDaoImpl roleDao, PermissionDaoImpl permDao) {
        this.roleDao = roleDao;
        this.permDao = permDao;
    }

    @Override
    public Role getRole(int roleId) {
        try {
           Role role =  roleDao.getRole(roleId);
           role.detach();
           return role;
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve role with roleId " + roleId + ": " + e.getMessage(), e);
            return null; 
        }
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            List<Role> roles = roleDao.getAllRolesWithPermissions();
            for (Role r : roles) {
                r.detach();
            }
            return roles;
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all roles: " + e.getMessage(), e);
            return Collections.emptyList(); 
        }
    }
}

