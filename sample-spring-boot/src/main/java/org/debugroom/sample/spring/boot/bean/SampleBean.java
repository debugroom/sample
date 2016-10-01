package org.debugroom.sample.spring.boot.bean;

import org.springframework.beans.factory.annotation.Autowired;

import org.debugroom.sample.spring.boot.repository.CompanyRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Slf4j
public class SampleBean {

	private String text;
	
	@Autowired
	CompanyRepository companyRepository;
	
	
	public void display(){
		log.info(this.getClass().getName() + " #display() called.");
		log.info(this.getClass().getName() + companyRepository.findAll());
	}

}
