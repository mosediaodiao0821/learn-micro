package com.dealmoon.ucenter.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.RequestContextFilter;


public class MyFilter extends RequestContextFilter {
	
	private Logger logger = LoggerFactory.getLogger(MyFilter.class);
	
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
    	logger.info("req_url ==>" + request.getRequestURI());
    }
}