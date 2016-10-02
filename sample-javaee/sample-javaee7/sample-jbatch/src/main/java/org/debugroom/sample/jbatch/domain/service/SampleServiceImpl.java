package org.debugroom.sample.jbatch.domain.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.debugroom.sample.jbatch.domain.model.entity.Company;
import org.debugroom.sample.jbatch.domain.repository.CompanyRepository;

@Named("sampleService")
public class SampleServiceImpl implements SampleService{

	@Inject
	CompanyRepository companyRepository;
	
	@Override
	public List<Company> getCompanyList() {
		return (List<Company>)companyRepository.findAll();
	}

}
