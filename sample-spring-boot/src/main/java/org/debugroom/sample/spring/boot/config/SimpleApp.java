package org.debugroom.sample.spring.boot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ConfigurableApplicationContext;

import org.debugroom.sample.spring.boot.bean.SampleBean;

@ComponentScan("org.debugroom.sample.spring.boot.config.infra")
@Configuration
@EnableAutoConfiguration
public class SimpleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				SimpleApp.class).web(false).run(args);
		SampleBean sampleBean = context.getBean(SampleBean.class);
		sampleBean.display();
	}
	
	@Bean SampleBean sampleBean(){
		return SampleBean.builder().build();
	}
}
