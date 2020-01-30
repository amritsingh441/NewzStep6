package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.newz.zuul.filter.JwtFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 * 
 * Add @EnableZuulProxy and @EnableEurekaClient
 * 
 */

public class NewzNetflixZuulApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewzNetflixZuulApiGatwayApplication.class, args);
	}

	
	/*
	 * Define the bean for Filter registration. Create a new FilterRegistrationBean
	 * object and use setFilter() method to set new instance of JwtFilter object.
	 * Also specifies the Url patterns for registration bean.
	 */


    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        return null;
    }
	

}
