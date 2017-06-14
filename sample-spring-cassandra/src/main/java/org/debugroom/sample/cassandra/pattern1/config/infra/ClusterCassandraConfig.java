package org.debugroom.sample.cassandra.pattern1.config.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.debugroom.sample.cassandra.config.env.CommonClusterConfig;

@Profile("cluster")
@Configuration
@EnableCassandraRepositories("org.debugroom.sample.cassandra.pattern1.domain.repository")
public class ClusterCassandraConfig extends CommonClusterConfig{

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.debugroom.sample.cassandra.pattern1.domain.entity" };
	}

	@Override
	public SchemaAction getSchemaAction(){
		return SchemaAction.RECREATE;
//		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}
