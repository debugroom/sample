package org.debugroom.sample.jbatch.app.batch.job;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.batch.api.AbstractBatchlet;

import org.debugroom.sample.jbatch.domain.model.entity.Company;
import org.debugroom.sample.jbatch.domain.service.SampleService;

@Named
public class SampleBatchlet extends AbstractBatchlet{

	@Inject
	JobContext jobContext;
	
	@Inject
	SampleService sampleService;
	
	@Override
	public String process() throws Exception {
		Properties properties = BatchRuntime.getJobOperator()
				.getParameters(jobContext.getExecutionId());
		System.out.println(this.getClass().getName() + " : param : " + properties.getProperty("param"));
		List<Company> companyList = sampleService.getCompanyList();
		System.out.println(this.getClass().getName() + ": companyList :");
		for(Company company : companyList){
			System.out.println(this.getClass().getName() + " :           - {" 
		+ company.getCompanyId() + ", "+ company.getCompanyName() + "}");
		}
		return null;
	}
	
}
