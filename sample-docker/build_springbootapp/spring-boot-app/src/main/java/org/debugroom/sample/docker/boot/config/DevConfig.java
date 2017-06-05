package org.debugroom.sample.docker.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import org.debugroom.sample.docker.boot.config.env.HSQLConfig;

@Configuration
@Profile("dev")
@Import(HSQLConfig.class)
public class DevConfig {
}
