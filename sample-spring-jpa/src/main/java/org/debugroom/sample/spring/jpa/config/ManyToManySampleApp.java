package org.debugroom.sample.spring.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;

import org.debugroom.sample.spring.jpa.domain.entity.Affiliation;
import org.debugroom.sample.spring.jpa.domain.entity.Group;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.service.ManyToManySampleService;
import org.debugroom.sample.spring.jpa.domain.service.ManyToManySampleServiceImpl;

@ComponentScan("org.debugroom.sample.spring.jpa.config.infra")
@Configuration
@EnableAutoConfiguration
public class ManyToManySampleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				ManyToManySampleApp.class).web(false).run(args);
		
		ManyToManySampleService service = context.getBean(ManyToManySampleService.class);
		User user = User.builder().userId("00000000").build();
		service.getGroups(user);
		Group group = Group.builder().groupName("org.debugroom").build();
		service.getUsersWith(group);
		service.getUsersWithout(group);
		User addUser = service.addUserTo(group, 
				User.builder().userName("NewComer(ΦωΦ)").affiliations(
						new HashSet<Affiliation>()).build());
		service.getGroups(addUser);
		service.getUsersWith(group);
		service.deleteUserFrom(group, addUser);
		service.getUsersWith(group);
		service.deleteGroup(group);
		service.getGroups(addUser);
		service.deleteUser(User.builder().userId("00000001").build());
		service.getUsersWith(Group.builder().groupName("nttdata").build());
	}
	
	@Bean ManyToManySampleService manyToManySampleService(){
		return new ManyToManySampleServiceImpl();
	}
}
