package org.debugroom.sample.spring.cloud.aws.config;

import org.debugroom.sample.spring.cloud.aws.domain.service.SampleServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
		"org.debugroom.sample.spring.cloud.aws.domain.service",
		"org.debugroom.sample.spring.cloud.aws.config"
})
@Configuration
@SpringBootApplication(exclude={ElastiCacheAutoConfiguration.class})
public class App {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				App.class).web(false).run(args);
		
		SampleServiceImpl sampleService = context.getBean(SampleServiceImpl.class);
		
		sampleService.send();

	}

}
