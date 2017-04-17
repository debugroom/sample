package org.debugroom.sample.cassandra.config;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan( {
	"org.debugroom.sample.cassandra.config", 
	"org.debugroom.sample.cassandra.common"})
@Configuration
@EnableAutoConfiguration
@Import(AspectConfig.class)
public class SimpleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				SimpleApp.class).web(false).run(args);
		SampleService sampleService = context.getBean(SampleService.class);
		sampleService.getUsers();
		sampleService.getUser("test");
		context.close();
	}
	
}
