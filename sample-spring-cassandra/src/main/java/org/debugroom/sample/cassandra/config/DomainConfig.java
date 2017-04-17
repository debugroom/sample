package org.debugroom.sample.cassandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.debugroom.sample.cassandra.config.infra.CassandraConfig;
import org.debugroom.sample.cassandra.domain.service.SampleService;

@Import(CassandraConfig.class)
public class DomainConfig {
}
