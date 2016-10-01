package org.debugroom.sample.cassandra.config;

import java.util.List;

import org.debugroom.sample.cassandra.entity.User;
import org.debugroom.sample.cassandra.service.SampleService;
import org.debugroom.sample.cassandra.service.SampleServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Session;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class SimpleApp {

	public static void main(String[] args){
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				SimpleApp.class).web(false).run(args);
		SampleService sampleService = context.getBean(SampleService.class);
		List<User> users = sampleService.getUsers();
		log.info(SimpleApp.class.getName() + " : users ");
		for(User user : users){
			log.info(SimpleApp.class.getName() + " :     - " + user.toString());
		}
	}
	
	@Bean SampleService sampleService(){
		return new SampleServiceImpl();
	}

	@ComponentScan
	@Configuration
	@EnableCassandraRepositories("org.debugroom.sample.cassandra.repository")
	static class CassandraConfig extends AbstractCassandraConfiguration{

		@Override
		protected String getKeyspaceName() {
			return "sample";
		}
		
		@Override
		public String[] getEntityBasePackages() {
			return new String[] { "org.debugroom.sample.cassandra.entity" };
		}

		@Override
		public SchemaAction getSchemaAction() {
			return SchemaAction.CREATE_IF_NOT_EXISTS;
		}

		@Bean
		public CassandraTemplate cassandraTemplate(Session session){
			return new CassandraTemplate(session);
		}

	}

}
