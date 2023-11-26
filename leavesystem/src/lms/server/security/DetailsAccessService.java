package lms.server.security;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lms.server.api.*;
import lms.shared.User;
import lms.shared.security.CustomGrantedAuthority;
import lms.shared.security.UserAccount;

public class DetailsAccessService implements UserDetailsService {
	
	private  UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = null;
		
		try {
			user = userService.getUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(user.getUsername());
		userAccount.setPassword(user.getPassword());
		
		List<CustomGrantedAuthority> authorities = new ArrayList<>();
		 
		authorities.add(new CustomGrantedAuthority(user.getRole().getRoleName())); 
		
		
		//add permissions list also into the useraccount authoritieslist soon 

		userAccount.setAuthorities(authorities);
		
		return userAccount;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
