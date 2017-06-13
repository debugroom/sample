package org.debugroom.sample.cassandra.pattern1.config;

import org.dozer.DozerBeanMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern1.domain.entity.User;
import org.debugroom.sample.cassandra.pattern1.domain.service.SampleServiceImpl;
import org.debugroom.sample.cassandra.pattern1.domain.service.UserDetailsServiceImpl;

@Configuration
@ComponentScan("org.debugroom.sample.cassandra.pattern1.config.infra")
public class DomainConfig {

	@Bean SampleService<User, Address, Email, Group> sampleService(){
		return new SampleServiceImpl();
	}

	@Bean UserDetailsService userDetailsService(){
		return new UserDetailsServiceImpl();
	}

	@Bean(name = "mapper")
    public DozerBeanMapper dozerBean() {
	    return new DozerBeanMapper();
	}

}
