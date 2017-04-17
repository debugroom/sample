package org.debugroom.sample.cassandra.pattern2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.debugroom.sample.cassandra.pattern2.config.infra.CassandraConfig;

@ComponentScan("org.debugroom.sample.cassandra.pattern2.domain")
@Import(CassandraConfig.class)
public class DomainConfig {
}
