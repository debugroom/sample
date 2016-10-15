package org.debugroom.sample.spring.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;

import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.EmailPK;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.service.OneToManySampleService;
import org.debugroom.sample.spring.jpa.domain.service.OneToManySampleServiceImpl;

@ComponentScan("org.debugroom.sample.spring.jpa.config.infra")
@Configuration
@EnableAutoConfiguration
public class OneToManySampleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				OneToManySampleApp.class).web(false).run(args);

		OneToManySampleService service = context.getBean(OneToManySampleService.class);
		String email = "test@test.com";
		User user = service.getUser(email);
   		service.getEmails(user);
		service.addEmail(user, "(ΦωΦ)@test.com");
		service.addEmail(user, "(ΦωΦ)@test.co.jp");
   		service.getEmails(user);
		User addUser = service.addUser(User.builder()
											.userName("(ΦωΦ)")
											.emails(new HashSet<Email>())
											.build(), email);
		service.getEmails(addUser);
		service.updateEmail(user, Email.builder()
				.id(EmailPK.builder().userId("00000000").emailId("1").build())
				.email("(・∀・)@test.com")
				.build());
   		service.getEmails(user);
		service.deleteEmail(user, Email.builder()
				.id(EmailPK.builder().userId("00000000").emailId("1").build())
				.email("(・∀・)@test.com")
				.build());
   		service.getEmails(user);
		service.deleteEmails(user);
		service.getEmails(user);
		service.deleteUser(addUser);
	}
	
	@Bean OneToManySampleService oneToManySampleService(){
		return new OneToManySampleServiceImpl();
	}

}
