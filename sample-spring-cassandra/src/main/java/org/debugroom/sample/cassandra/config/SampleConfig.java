package org.debugroom.sample.cassandra.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("sample")
@Import(DomainConfig.class)
public class SampleConfig {
	
	@PostConstruct
	public void setUp(){
		log.info(this.getClass().getName() + " print out : profile is sample");
	}

}
