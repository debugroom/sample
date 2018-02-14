package org.debugroom.sample.spring.cloud.aws.config;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/static/**", "/webjars/**")
		        .addResourceLocations("classpath:/static/",
		        		"classpath:/META-INF/resources/webjars/")
		        .resourceChain(false);
	}

}
