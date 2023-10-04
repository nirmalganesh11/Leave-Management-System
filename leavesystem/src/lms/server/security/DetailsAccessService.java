package lms.server.security;


import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lms.server.serverserviceinterfaces.*;
import lms.shared.User;
import lms.shared.security.CustomGrantedAuthority;
import lms.shared.security.UserAccount;

public class DetailsAccessService implements UserDetailsService {
	
	private  UserService userServ;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userServ.getUser(username);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(user.getUsername());
		userAccount.setPassword(user.getPassword());
		
		List<CustomGrantedAuthority> authorities = new ArrayList<>();
		 
		authorities.add(new CustomGrantedAuthority(user.getRole().getRoleName())); 
		
		
		//add permissions list also into the useraccount authoritieslist soon 

		userAccount.setAuthorities(authorities);
		
		return userAccount;
	}

	public UserService getUserServ() {
		return userServ;
	}

	public void setUserServ(UserService userServ) {
		this.userServ = userServ;
	}
	
}
