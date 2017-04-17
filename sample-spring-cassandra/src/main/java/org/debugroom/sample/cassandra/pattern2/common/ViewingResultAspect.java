package org.debugroom.sample.cassandra.pattern2.common;

import org.aspectj.lang.annotation.Aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Credential;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ViewingResultAspect {

	@Pointcut("execution(* org.debugroom.sample.cassandra.domain.service.SampleService.getUsers(..))")
	public void pointcut(){
	}

	@AfterReturning(value="pointcut()", returning="list")
	public void afterReturning(JoinPoint joinPoint, List<?> list){
		List<User> users = (List<User>)list;
		log.info(this.getClass().getName() + " : users");
		for(User user : users){
			log.info(this.getClass().getName() + " : - user id " + user.getUserId());
			log.info(this.getClass().getName() + " : - user name " + user.getUserName());
			log.info(this.getClass().getName() + " : - user login id " + user.getLoginId());
			log.info(this.getClass().getName() + " : - current zip cd " + user.getAddress().getZipCd());
			log.info(this.getClass().getName() + " : - current address " + user.getAddress().getAddress());
			for(Credential credential : user.getCredentials()){
				log.info(this.getClass().getName() + " : - " + credential.getCredentialType() 
				+ " : " + credential.getCredentialKey()
				+ " : expired date : " + credential.getExpiredDate());
			}
		}
	}

}
