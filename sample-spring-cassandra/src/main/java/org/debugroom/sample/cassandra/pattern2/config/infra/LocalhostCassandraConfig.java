package org.debugroom.sample.cassandra.pattern2.config.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.debugroom.sample.cassandra.config.env.CommonLocalhostConfig;

@Profile("localhost")
@Configuration
@EnableCassandraRepositories("org.debugroom.sample.cassandra.pattern2.domain.repository")
public class LocalhostCassandraConfig extends CommonLocalhostConfig{

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.debugroom.sample.cassandra.pattern2.domain.entity" };
	}

	@Override
	public SchemaAction getSchemaAction(){
		return SchemaAction.RECREATE;
//		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}
