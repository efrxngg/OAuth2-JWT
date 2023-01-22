package com.empresax.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile(value = "prod")
@Configuration
@EnableTransactionManagement
public class MariaDBConfig {

    @Bean
    public DataSource getSecurityEmpresaxDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("org.mariadb.jdbc.Driver");
        source.setUrl("jdbc:mariadb://127.0.0.1:3307/security_empresax");
        source.setUsername("root");
        source.setPassword("root1234");
        return source;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getSecurityEmpresaxDataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getSecurityEmpresaxDataSource());
    }

}
