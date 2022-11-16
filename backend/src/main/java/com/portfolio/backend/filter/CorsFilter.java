package com.portfolio.backend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/**")
public class CorsFilter implements Filter {

	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
    		throws IOException, ServletException 
    {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        httpServletResponse.addHeader("Access-Control-Allow-Origin","http://localhost:3000");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,PATCH,DELETE");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","Content-Type','Authorization");
        
        chain.doFilter(request, response);
    }

}