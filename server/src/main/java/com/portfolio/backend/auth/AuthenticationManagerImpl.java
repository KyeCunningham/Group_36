package com.portfolio.backend.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.portfolio.backend.service.UserDetailsServiceImpl;

public class AuthenticationManagerImpl implements AuthenticationManager {


	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		if (authentication.getClass().equals(UsernamePasswordAuthenticationToken.class))
		{
			String username = authentication.getPrincipal().toString();
			String password =authentication.getCredentials().toString();
			
			// todo: remove admin authentication
			if (username.equals("admin") && password.equals("password"))
			{
				ArrayList<Role> auths = new ArrayList<Role>();
				auths.add(new Role());
				authentication = new UsernamePasswordAuthenticationToken(
						authentication.getPrincipal(), 
						authentication.getCredentials(), 
						auths);
				
				return authentication;
			}
			
			UserDetails user = userDetailsServiceImpl.loadUserByUsername(authentication.getPrincipal().toString());
			
			if(user.getPassword().equals(authentication.getCredentials().toString()))
			{
				ArrayList<Role> auths = new ArrayList<Role>();
				auths.add(new Role());
				authentication = new UsernamePasswordAuthenticationToken(
					authentication.getPrincipal(), 
					authentication.getCredentials(), 
					auths);
			
				return authentication;	
			}
			else 
			{
				// invalid password. but dont say that. 
				throw new BadCredentialsException("invalid username or password"); 
			}
		}
		
		
		// couldnt decide on if its valid or not
		return null;
	}

}
