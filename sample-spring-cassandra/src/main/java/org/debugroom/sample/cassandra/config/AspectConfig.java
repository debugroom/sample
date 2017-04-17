package org.debugroom.sample.cassandra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.debugroom.sample.cassandra.common")
public class AspectConfig {
}
