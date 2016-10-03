package org.debugroom.sample.spring.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.ConfigurableApplicationContext;

import org.debugroom.sample.spring.jpa.domain.service.SimpleAccessService;
import org.debugroom.sample.spring.jpa.domain.service.SimpleAccessServiceImpl;

@ComponentScan("org.debugroom.sample.spring.jpa.config.infra")
@Configuration
@EnableAutoConfiguration
public class SampleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				SampleApp.class).web(false).run(args);

		SimpleAccessService simpleAccessService = context.getBean(SimpleAccessService.class);
		
		simpleAccessService.getUsers();
		simpleAccessService.getAddressList();
		simpleAccessService.getEmails();
		simpleAccessService.getGroups();
		
	}

	@Bean SimpleAccessService sampleService(){
		return new SimpleAccessServiceImpl();
	}

}
