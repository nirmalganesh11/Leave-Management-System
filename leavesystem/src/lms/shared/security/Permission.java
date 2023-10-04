package lms.shared.security;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Permission implements IsSerializable{
	
	private int permissionId;
	
	private String permissionName;
	
	private String permissionDescription;
	
	public Permission() {
		
	}
	
	public Permission(String permissionName,String permissionDescription) {
		
		this.permissionName = permissionName;
		this.permissionDescription = permissionDescription;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
}
