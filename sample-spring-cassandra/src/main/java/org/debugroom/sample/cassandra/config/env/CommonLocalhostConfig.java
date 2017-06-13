package org.debugroom.sample.cassandra.config.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.debugroom.sample.cassandra.config.infra.CommonCassandraConfig;

@Configuration
@Profile("localhost")
public class CommonLocalhostConfig extends CommonCassandraConfig{

	@Override
	protected String getKeyspaceName() {
		return "sample";
	}

}
