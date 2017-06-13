package org.debugroom.sample.cassandra.pattern2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.debugroom.sample.cassandra.pattern2.domain", 
	"org.debugroom.sample.cassandra.pattern2.config.infra"})
public class DomainConfig {
}
