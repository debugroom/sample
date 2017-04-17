package org.debugroom.sample.javaee6.web.sample;

import javax.ejb.EJB;

import javax.inject.Inject;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import org.debugroom.sample.javaee6.service.SampleService;
import org.debugroom.sample.javaee6.domain.model.entity.User;

import lombok.Data;

@Data
@Model
public class SampleBean {
	
	private String text;
	private String companyId;
	private String userId;
	private User user;
	
	@EJB
	@Inject
	private SampleService sampleService;
	
	public String next(){
		user = sampleService.findUserService(companyId, userId);
		return "output.xhtml";
	}
	
	public void display(){
		System.out.println("CompanyId : " + companyId);
		System.out.println("UserId : " + userId);
	}

	@Produces
	@Model
	public User getUser(){
		return user;
	}

	public String test(){
		user = sampleService.findUserService(companyId, userId);
		return "output.xhtml";
	}

}
