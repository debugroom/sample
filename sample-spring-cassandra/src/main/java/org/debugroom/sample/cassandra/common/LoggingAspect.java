package org.debugroom.sample.cassandra.common;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* org.debugroom.sample.cassandra..*Service.*(..))")
	public void pointcut(){
	}

	@Before("pointcut()")
	public void before(){
		log.info(this.getClass().getName() + " before advice : method start");
	}

	@AfterReturning(value="pointcut()", returning="list")
	public void afterReturning(JoinPoint joinPoint, List<?> list){
		log.info(this.getClass().getName() + " afterreturning advice : " + list.toString());
	}

	@After("pointcut()")
	public void after(){
		log.info(this.getClass().getName() + " after advice : method start");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		log.info(this.getClass().getName() + " around advice : method start");
		return proceedingJoinPoint.proceed();
	}

}
