package com.professor_compilation.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * Config for data base
 */
@Configuration
public class DatabaseConfig {


    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Bean entry point
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    /**
     * Bean for interface JdbcOperations
     * @param dataSource - entry point in data base
     * @return JdbcOperations
     */
    @Bean
    @Primary
    public JdbcOperations template(final DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(final DataSource dataSource) {
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(dataSource);
        return template;
    }

}

