package com.flab.inventory.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.inventory.domain")
@EnableJpaRepositories(basePackages = "com.flab.inventory.infrastructure.persistence.jpa")
public class JpaInventoryConfig {

}
