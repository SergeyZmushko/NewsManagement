package com.mjc.school.repository.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableAutoConfiguration
@EntityScan(basePackages = "com/mjc/school/repository/model")
public class Configuration {

}
