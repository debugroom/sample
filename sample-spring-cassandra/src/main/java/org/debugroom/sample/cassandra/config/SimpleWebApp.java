package org.debugroom.sample.cassandra.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.debugroom.sample.cassandra.config.mvc.MvcConfig;
import org.debugroom.sample.cassandra.config.security.SecurityConfig;

@Import({MvcConfig.class, AspectConfig.class, SecurityConfig.class})
@SpringBootApplication(scanBasePackages="org.debugroom.sample.cassandra.config")
public class SimpleWebApp extends SpringBootServletInitializer{

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(
				SimpleWebApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(SimpleWebApp.class);
	}

}
