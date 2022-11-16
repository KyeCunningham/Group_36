package com.portfolio.backend.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtToken {

	@Value("{jwt.key}")
	private String key;
	
	
}
