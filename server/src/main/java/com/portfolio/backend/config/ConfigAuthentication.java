package com.portfolio.backend.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.portfolio.backend.auth.AuthenticationManagerImpl;

@Configuration
public class ConfigAuthentication {
 
	// define permissions required to view pages.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	
    	http
    		// this is temporary. a real solution is to pass in the csrf token with a request
    		.csrf().disable()
    		.cors().disable()
    		
    		.authorizeRequests()
    		
    		// these paths need to change so that i can put the base react frontend at root
    		// the react frontend should just use api calls into the backend to serve the content, as well
    		// as providing some sort of authentication with each request.
    		// 
    		// 
    		
    		
    		// get api/user/id returns the public profile of a user
    		// get api/post/id returns the public info on a post. things like views and like/dislike are hidden
    		// get api/bill/id returns the public info on a medical bill / other expense
    		// get api/
    		.antMatchers("/api/**").permitAll() 
    		.antMatchers("/user/**").authenticated() // no these do need to change. lol
    		.antMatchers("/admin/**").hasRole("admin") 
    		.and()
    		.formLogin()
    		.and()
    		.httpBasic();
    	
    	
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    	
    	return new AuthenticationManagerImpl();
    }

    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
    	
    	JdbcUserDetailsManager profiles = new JdbcUserDetailsManager(dataSource);
        return profiles;
    }
}
