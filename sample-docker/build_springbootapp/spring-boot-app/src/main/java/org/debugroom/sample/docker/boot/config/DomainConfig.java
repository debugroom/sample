package org.debugroom.sample.docker.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.debugroom.sample.docker.boot.domain.service.SampleService;
import org.debugroom.sample.docker.boot.domain.service.SampleServiceImpl;

@Configuration
public class DomainConfig {
	
	@Bean
	public SampleService sampleService(){
		return new SampleServiceImpl();
	}

}
