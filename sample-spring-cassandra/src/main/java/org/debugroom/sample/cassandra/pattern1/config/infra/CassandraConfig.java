package org.debugroom.sample.cassandra.pattern1.config.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.debugroom.sample.cassandra.config.infra.CommonCassandraConfig;

@Profile("pattern1")
@Configuration
@EnableCassandraRepositories("org.debugroom.sample.cassandra.pattern1.domain.repository")
public class CassandraConfig extends CommonCassandraConfig{

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.debugroom.sample.cassandra.pattern1.domain.entity" };
	}

	@Override
	protected String getKeyspaceName() {
		return "sample";
	}
	
	@Override
	public SchemaAction getSchemaAction(){
		return SchemaAction.RECREATE;
//		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}
