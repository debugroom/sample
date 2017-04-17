package org.debugroom.sample.cassandra.pattern2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.debugroom.sample.cassandra.pattern2.common")
public class AspectConfig {
}
