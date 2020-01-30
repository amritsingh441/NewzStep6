package com.stackroute.newz;



import org.springframework.boot.SpringApplication;

	/*
	 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
	 * and @ComponentScan with their default attributes
	 * 
	 * Add @EnableEurekaServer
	 * 
	 */

public class NewzNetflixEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewzNetflixEurekaServerApplication.class, args);
	}
}
