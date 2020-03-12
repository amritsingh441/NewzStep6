package com.stackroute.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */

@Aspect
@Component
@Configuration
@EnableAspectJAutoProxy
public class LoggerAspect {
	/*
	 * Write loggers for each of the methods of User controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Bean
	public LoggerAspect getAspect() {
		return new LoggerAspect();
	}
	
	@Before("execution(* com.stackroute.user.controller.*.*(..)))")
	public void beforeAdviceImplMethod(JoinPoint jp){
		logger.debug("Executing beforeAdviceImplMethod() "+ jp.getSignature().getName());
	}
	@After("execution(* com.stackroute.user.controller.*.*(..)))")
	public void afterAdviceImplMethod(JoinPoint jp){
		logger.debug("Executing afterAdviceImplMethod() "+ jp.getSignature().getName());
	}
	@AfterReturning("execution(* com.stackroute.user.controller.*.*(..)))")
	public void afterReturningAdviceImplMethod(JoinPoint jp){
		logger.debug("Executing afterReturningAdviceImplMethod() "+ jp.getSignature().getName());
	}
	@AfterThrowing("execution(* com.stackroute.user.controller.*.*(..)))")
	public void afterThrowingAdviceImplMethod(JoinPoint jp){
		logger.debug("Executing afterThrowingAdviceImplMethod() "+ jp.getSignature().getName());
	}
}