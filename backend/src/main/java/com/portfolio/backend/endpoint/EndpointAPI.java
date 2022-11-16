package com.portfolio.backend.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.domain.Profile;
import com.portfolio.backend.model.LoginRequest;
import com.portfolio.backend.model.LoginResponse;
import com.portfolio.backend.model.RegisterRequest;
import com.portfolio.backend.model.RegisterResponse;
import com.portfolio.backend.repository.ProfileRepository;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api")
public class EndpointAPI {

	@Autowired
	private ProfileRepository profiles;
	


	@PostMapping("/register")
	public RegisterResponse test3(@RequestBody RegisterRequest request )
	{	
		RegisterResponse response = new RegisterResponse();
		response.status = 1L;
		response.token = "an account with this username already exists";
		
		
		Optional<Profile> found = profiles.findByUsername(request.username);
		
		if (found.isEmpty())
		{
			// password checking has to be done on the server side.
			if (request.username.length() <= 4)
			{
				response.token = "username must be greater than 4 characters";
			}
			else if (request.password.length() <= 4)
			{
				response.token = "password must be greater than 4 characters";
			}
			else 
			{				
				Profile user = new Profile();
				user.password = request.password; // todo: encode
				user.username = request.username;
				// todo: add user roles
				
				profiles.save(user);
				
				response.status = 0L;
				response.token = ""; // todo: set the response token for persistent signin
			}
		}
		
		
		return response;
	}
	

	@PostMapping("/login")
	public LoginResponse test4(@RequestBody LoginRequest request )
	{	
		LoginResponse response = new LoginResponse();
		response.status = 1L;
		response.token = "";
		
		Optional<Profile> found = profiles.findByUsername(request.username);
		
		if (found.isEmpty())
		{
			response.status = 1L; // failed to find user account
			response.token = "invalid username or password";
			return response;
		}
		
		if (!found.get().password.equals(request.password))
		{
			// the account exists, but return the same message so that the 'hacker' doesn't know.
			// don't give information that can compromise account security.
			response.status = 1L;
			response.token = "invalid username or password";
		}
		else 
		{
			response.status = 0L;
			response.token = ""; // todo: set the response token for persistent signin	
		}
		
		
		return response;
	}
	
	
}
