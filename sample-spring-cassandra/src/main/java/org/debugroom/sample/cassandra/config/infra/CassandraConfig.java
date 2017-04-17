package org.debugroom.sample.cassandra.config.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@Profile("sample")
@EnableCassandraRepositories("org.debugroom.sample.cassandra.domain.repository")
public class CassandraConfig extends CommonCassandraConfig{

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.debugroom.sample.cassandra.domain.entity" };
	}

	@Override
	protected String getKeyspaceName() {
		return "sample";
	}

}
