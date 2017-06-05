package org.debugroom.sample.docker.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.debugroom.sample.docker.boot.config.infra.JpaConfig;

@Profile("jpa")
@Import(JpaConfig.class)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.debugroom.sample.docker.boot.domain.repository")
public class InfraConfig {
}
