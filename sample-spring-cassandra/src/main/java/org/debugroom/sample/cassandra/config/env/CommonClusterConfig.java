package org.debugroom.sample.cassandra.config.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

import org.debugroom.sample.cassandra.config.infra.CommonCassandraConfig;

@Configuration
@Profile("cluster")
public class CommonClusterConfig extends CommonCassandraConfig{

    @Autowired
    private Environment env;

	@Override
	protected String getKeyspaceName() {
		return "sample";
	}

	@Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(env.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));
        return cluster;
    }

}
