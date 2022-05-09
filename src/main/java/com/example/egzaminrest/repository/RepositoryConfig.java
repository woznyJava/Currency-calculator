package com.example.egzaminrest.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.consol.citrus.simulator.repository")
@EntityScan({"com.consol.citrus.simulator.model"})
public class RepositoryConfig {
}
