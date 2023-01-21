package com.developer.leonardo.migracaodedadosspringbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDataSouce() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSouce() {
        return DataSourceBuilder.create().build();
    }

}
