package org.debugroom.sample.spring.boot.config.infra.env;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("dev")
public class DBConfigDev {

	@Bean
	public DataSource dataSource(){
		return (new EmbeddedDatabaseBuilder())
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:ddl/schema.sql")
				.addScript("classpath:ddl/data.sql")
				.build();
	}

}
