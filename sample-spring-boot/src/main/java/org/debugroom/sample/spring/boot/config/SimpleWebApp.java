package org.debugroom.sample.spring.boot.config;

import org.debugroom.sample.spring.boot.service.SampleService;
import org.debugroom.sample.spring.boot.service.SampleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.debugroom.sample.spring.boot.config.mvc.MvcConfig;

@ComponentScan("org.debugroom.sample.spring.boot.config.infra")
@Configuration
@Import(MvcConfig.class)
@SpringBootApplication
public class SimpleWebApp{

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(SimpleWebApp.class, args); 
	}
	
	@Bean
	public SampleService sampleService(){
		return new SampleServiceImpl();
	}

}
