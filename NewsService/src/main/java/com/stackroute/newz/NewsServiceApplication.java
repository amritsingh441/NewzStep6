package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */

@EnableEurekaClient
@SpringBootApplication
public class NewsServiceApplication {
    
	/*
	 * You need to run SpringApplication.run, because this method start whole spring
	 * framework. Code below integrates your main() with SpringBoot
	 */

	public static void main(String[] args) {
		SpringApplication.run(NewsServiceApplication.class, args);
	}

}
