package com.zh.sergei.specalc.user.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${app.datasource.main.driver-class-name}")
    String driverClassName;

    @Value("${app.datasource.main.jdbc-url}")
    String dbUrl;

    @Value("${app.datasource.main.username}")
    String dbUserName;

    @Value("${app.datasource.main.password}")
    String dbPassword;

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    // alternative way to create datasource with explicit properties
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(dbUrl)
                .username(dbUserName)
                .password(dbPassword)
                .build();
    }
}
