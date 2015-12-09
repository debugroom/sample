package org.debugroom.sample.javaee6.web.sample;

import java.util.List;
import java.io.Serializable;

import javax.ejb.EJB;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import org.debugroom.sample.javaee6.service.SampleService;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.Address;

import lombok.Data;

@Data
@Named
@SessionScoped
public class SampleDataTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Address> addresses;
	private User specifiedUser;
	private String companyId = "0000000000";
	private String userId = "00000000";
	
	@EJB
	@Inject
	private SampleService sampleService;
	
	public String next(){
		specifiedUser = sampleService.findUserService(companyId,  userId);
		return "/jsf/sample/multiColumnsLayoutSample2.xhtml";
	}
	
	public String search(){
		addresses = sampleService.findAddressService(companyId, userId);
		return "/jsf/sample/multiColumnsLayoutSample3.xhtml";
	}
	
	@Produces
	@Model
	public User getSpecifiedUser(){
		return specifiedUser;
	}

	@Produces
	@Model
	public List<Address> getAddresses(){
		return addresses;
	}
}
