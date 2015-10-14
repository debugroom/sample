package org.debugroom.sample.javaee6.web.sample;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.debugroom.sample.javaee6.service.SampleService;

import lombok.Data;

@Data
@Model
public class SampleBean {
	
	private String text;
	private String companyId;
	private String userId;
	
	@Inject
	private SampleService sampleService;
	
	
	public String next(){
		sampleService.findUserService(companyId, userId);
		return "output.xhtml";
	}
	
	public void display(){
		System.out.println("CompanyId : " + companyId);
		System.out.println("UserId : " + userId);
	}

}
