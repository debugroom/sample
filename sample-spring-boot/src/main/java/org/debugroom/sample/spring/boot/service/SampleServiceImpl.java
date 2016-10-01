package org.debugroom.sample.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.debugroom.sample.spring.boot.entity.Company;
import org.debugroom.sample.spring.boot.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("sampleServiceImpl")
public class SampleServiceImpl implements SampleService{

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public List<Company> getCompanies() {
		log.info(this.getClass().getName() + "#display() called.");
		log.info(this.getClass().getName() + "Company List : ");
		List<Company> companies = companyRepository.findAll();
		for(Company company : companies){
			log.info(this.getClass().getName() + "             : " + company.toString());
		}
		return companies;
	}
}
