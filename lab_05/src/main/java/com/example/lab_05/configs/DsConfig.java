package com.example.lab_05.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DsConfig {
    @Bean
    @Scope("singleton")
    public DataSource mariadbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost:3306/lab_05?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("sapassword");
        return dataSource;
    }
}