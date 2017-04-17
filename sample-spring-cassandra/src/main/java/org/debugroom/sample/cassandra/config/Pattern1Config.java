package org.debugroom.sample.cassandra.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.debugroom.sample.cassandra.pattern1.config.DomainConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import org.debugroom.sample.cassandra.pattern1.config.AspectConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("pattern1")
@Import({DomainConfig.class, AspectConfig.class})
public class Pattern1Config {
	
	@PostConstruct
	public void setUp(){
		log.info(this.getClass().getName() + " print out : profile is pattern1");
	}

	@PreDestroy
	public void tearDown(){
		log.info(this.getClass().getName() + " print out : destoryed");
	}
}
