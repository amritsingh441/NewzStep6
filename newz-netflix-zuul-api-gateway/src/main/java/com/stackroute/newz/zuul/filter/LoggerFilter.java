package com.stackroute.newz.zuul.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * Implement zuul logging filter by extending zuul filter
 */
@Component
public class LoggerFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
}
