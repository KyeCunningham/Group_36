package com.portfolio.backend.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class EndpointUser {

	@GetMapping("/home")
	public String test1()
	{
		return "Hello home!";
	}

	@GetMapping("/dashboard")
	public String test2()
	{
		return "Hello user!";
	}
	
}
