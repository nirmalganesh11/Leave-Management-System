package lms.server;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import lms.client.clientservices.UserServiceClient;
import lms.server.serverserviceinterfaces.UserService;
import lms.shared.User;



public class UserServiceImplServlet extends RemoteServiceServlet implements UserServiceClient{

	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private UserService userServ;
	
	String username;

	
	public UserServiceImplServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		userServ = context.getBean(UserService.class);
	}
	
	@Override
	public User authenticate(String username, String password) {
		
		return null;
	}
	
	public String saveUser(User user) {
		return userServ.saveUser(user);
	}
	
	
	
	@Override
	public String getLoggedInUser() {
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication != null && authentication.isAuthenticated()) {
	            Object principal = authentication.getPrincipal();
	            
	            if (principal instanceof UserDetails) {
	                UserDetails userDetails = (UserDetails) principal;
	                String str="";
	                for(GrantedAuthority authority : userDetails.getAuthorities())
	                {
	                	str = str + " "+authority.getAuthority();
	                }
	                return username = userDetails.getUsername()+str+"--"+authentication.isAuthenticated();
	               
	            } else {
	                return username = principal.toString()+authentication.isAuthenticated();
	                
	            }
	        } else {
	        	return "user is not authenticated";
	        }
	}

	@Override
	public User getUser(String username) {
		return userServ.getUser(username);
	}

	@Override
	public Boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth !=null && auth.isAuthenticated()) {
			
			Object principal = auth.getPrincipal();
			
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				if(userDetails.getUsername().equals("anonymous") || userDetails.getUsername().equals("anonymousUser") ) {
					return false;
				}
				return true;
			}
				
		}
		return false;
	}

	@Override
	public User getAuthenticatedUser() {
		
		User user = null;
		if(isAuthenticated()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String username = userDetails.getUsername();
				 user = getUser(username);
			}
		}
		return user;
	}

	@Override
	public String logoutUser() {
		AnonymousAuthenticationToken anonymousAuthentication = new AnonymousAuthenticationToken(
	            "anonymousUser", "anonymousUser", 
	            new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_ANONYMOUS")))
	        );

	        
	    SecurityContextHolder.getContext().setAuthentication(anonymousAuthentication);

	    return "redirect:/logout-success";
	}
	
	
	
	
	
}
