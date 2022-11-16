package com.portfolio.backend.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.portfolio.backend.domain.Profile;
import com.portfolio.backend.model.RegisterRequest;
import com.portfolio.backend.model.RegisterResponse;
import com.portfolio.backend.repository.ProfileRepository;

@RestController
public class EndpointHome 
{

	@Autowired
	private ProfileRepository profiles;
	
	@GetMapping("/home")
	public String test1()
	{
		return "Hello home!";
	}
	
	// this is technically not needed if i just set page priveledges properly, but its new to me and im practicing
	@GetMapping("/dashboard")
	public RedirectView test2(RedirectAttributes r)
	{
		return new RedirectView("login");
	}
}
