package org.debugroom.sample.cassandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

import org.debugroom.sample.cassandra.pattern2.config.DomainConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.debugroom.sample.cassandra.pattern2.config.AspectConfig;

@Slf4j
@Configuration
@Profile("pattern2")
@Import({DomainConfig.class, AspectConfig.class})
public class Pattern2Config {

	@PostConstruct
	public void setUp(){
		log.info(this.getClass().getName() + " print out : profile is pattern2");
	}

	@PreDestroy
	public void tearDown(){
		log.info(this.getClass().getName() + " print out : destoryed");
	}
}
