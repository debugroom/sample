package org.debugroom.sample.spring.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import org.debugroom.sample.spring.jpa.domain.entity.User;
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
		
		List<User> users = simpleAccessService.getUsers();
		simpleAccessService.getAddressList();
		simpleAccessService.getEmails();
		simpleAccessService.getGroups();
		simpleAccessService.getUser(users.get(0).getUserId());
		simpleAccessService.getAddress(users.get(0));
		simpleAccessService.getEmails(users.get(0));
		simpleAccessService.getGroup("org.debugroom");
		
		
	}

	@Bean SimpleAccessService sampleService(){
		return new SimpleAccessServiceImpl();
	}

}
