package lms.server.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class ProviderDAO extends DaoAuthenticationProvider {
		
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication authUser = null;
        
        UserDetails userDetails = getUserDetails(username);
        
        System.out.println(userDetails.getPassword()+"----"+userDetails.getUsername()+"----"+userDetails.getAuthorities().toString());
        
        if(userDetails != null && userDetails.getPassword().equals(password)) {
        	System.out.println("its coming here");
        	authUser = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        	SecurityContextHolder.getContext().setAuthentication(authUser);	
        }
        
        return authUser;
    }
	

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }

    private UserDetails getUserDetails(String username) {
    	
		UserDetailsService inMethodService = getUserDetailsService();

		UserDetails fulldetails = inMethodService.loadUserByUsername(username);
		
		//System.out.println("is it coming here bruvvvvvvvv"+fulldetails.getPassword());
		
		return fulldetails;
	}
	
}
