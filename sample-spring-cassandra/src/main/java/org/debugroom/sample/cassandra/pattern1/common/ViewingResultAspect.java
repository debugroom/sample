package org.debugroom.sample.cassandra.pattern1.common;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import org.debugroom.sample.cassandra.pattern1.domain.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ViewingResultAspect {

	@Pointcut("execution(* org.debugroom.sample.cassandara.domain.service.*.*(..))")
	public void pointcut(){
	}

	@AfterReturning(value="pointcut()", returning="list")
	public void afterReturning(JoinPoint joinPoint, List<?> list){
		log.info(this.getClass().getName() + "#afterReturning() executed.");
		for(Object object : list){
			if(object instanceof User){
				User user = (User)object;
				log.info(this.getClass().getName() + " : - user id " + user.getUserId());
				log.info(this.getClass().getName() + " : - user name " + user.getUserName());
				log.info(this.getClass().getName() + " : - user address" + user.getAddress().toString());
			}
		}
		
	}
}
