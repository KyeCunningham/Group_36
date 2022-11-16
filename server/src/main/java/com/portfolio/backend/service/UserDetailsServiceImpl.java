package com.portfolio.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.backend.auth.Role;
import com.portfolio.backend.domain.Profile;
import com.portfolio.backend.repository.ProfileRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ProfileRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	Profile user = repo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
			
	ArrayList<GrantedAuthority> auths = new ArrayList<>();
	auths.add(new Role());
	
	return new User(user.username,user.password, auths);
	}

	
}
