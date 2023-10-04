package lms.shared.security;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Role implements IsSerializable{
	
	private int roleId;
	
	private String roleName;

	
	private List<Permission> permissions;
	
	
	public Role() {
		
	}
	
	public Role(String roleName,List<Permission> permissions) {
		
		this.roleName = roleName;
		this.permissions = permissions;	
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
