package org.debugroom.sample.cassandra.config.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Session;

@Configuration
public abstract class CommonCassandraConfig extends AbstractCassandraConfiguration{

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Bean
	public CassandraTemplate cassandraTemplate(Session session){
		return new CassandraTemplate(session);
	}

	@Bean
	public  CassandraAdminOperations cassandraAdminOperations() throws ClassNotFoundException{
		return new CassandraAdminTemplate(session().getObject(), cassandraConverter());
	}

}
