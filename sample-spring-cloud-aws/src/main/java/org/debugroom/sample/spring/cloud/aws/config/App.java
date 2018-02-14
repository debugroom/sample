package org.debugroom.sample.spring.cloud.aws.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class App {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				App.class).web(false).run(args);
	}

}
