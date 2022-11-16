package com.portfolio.backend.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class EndpointAdmin {

	@GetMapping("/home")
	public String test1()
	{
		return "admin home!";
	}

	@GetMapping("/dashboard")
	public String test2()
	{
		return "admin dashboard!";
	}
	
}
