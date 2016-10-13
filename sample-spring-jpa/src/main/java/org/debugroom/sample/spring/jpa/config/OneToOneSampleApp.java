package org.debugroom.sample.spring.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ConfigurableApplicationContext;
import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.service.OneToOneSampleService;
import org.debugroom.sample.spring.jpa.domain.service.OneToOneSampleServiceImpl;

@ComponentScan("org.debugroom.sample.spring.jpa.config.infra")
@Configuration
@EnableAutoConfiguration
public class OneToOneSampleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				OneToOneSampleApp.class).web(false).run(args);
		
		OneToOneSampleService service = context.getBean(OneToOneSampleService.class);
		String zipCd = "135-8670";
		service.getUsersWith(zipCd);
		service.getUsersWithout(zipCd);
		User user = service.addAddress(
				Address.builder().zipCd("000-0000").address("Tokyo").build());
		service.updateAddress(user.getUserId(), 
				Address.builder().zipCd("100-1000").address("Japan").build());
		service.getAddress(user);
		service.deleteAddress(user.getUserId());
		service.getAddress(user);
//		service.deleteUser(user.getUserId());
		service.deleteUser("00000000");
	}

	@Bean OneToOneSampleService oneToOneSampleService(){
		return new OneToOneSampleServiceImpl();
	}

}
