package org.debugroom.sample.spring.cloud.aws.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

//@Import(MvcConfig.class)
//@SpringBootApplication(exclude={ElastiCacheAutoConfiguration.class})
public class WebApp extends SpringBootServletInitializer{

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(
				WebApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebApp.class);
	}


}
