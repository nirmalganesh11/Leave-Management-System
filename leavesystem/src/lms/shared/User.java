package lms.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import lms.shared.security.Role;

public class User implements IsSerializable{
	
	private int userId;
	
	private String username;
	
	private String password;
	
	private Role role;
	
	
	public User() {
		
	}
	
	public User(String username,String password,Role role) {
		
		this.username = username;
		this.password = password;
		this.role = role;
	
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

}
