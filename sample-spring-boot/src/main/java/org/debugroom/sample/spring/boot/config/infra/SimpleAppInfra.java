package org.debugroom.sample.spring.boot.config.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.debugroom.sample.spring.boot.repository")
public class SimpleAppInfra {
}
