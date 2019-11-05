package com.wotung.integration.gym.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.wotung.integration.gym.properties.DSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Primary
@EnableConfigurationProperties(DSProperties.class)
public class DuridDataSourceConfiguration {
    @Autowired
    private org.springframework.boot.autoconfigure.jdbc.DataSourceProperties DataSourceProperties;

    @Autowired
    private DSProperties dsProperties;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(DataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(DataSourceProperties.getUrl());
        druidDataSource.setUsername(DataSourceProperties.getUsername());
        druidDataSource.setPassword(DataSourceProperties.getPassword());
        druidDataSource.setMaxActive(dsProperties.getMaxActive());
        druidDataSource.setInitialSize(dsProperties.getInitialSize());
        druidDataSource.setMinIdle(dsProperties.getMinIdle());
        druidDataSource.setTestOnBorrow(dsProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(dsProperties.isTestOnReturn());
        druidDataSource.setTestWhileIdle(dsProperties.isTestWhileIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dsProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dsProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setPoolPreparedStatements(dsProperties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dsProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setMaxOpenPreparedStatements(dsProperties.getMaxOpenPreparedStatements());
        druidDataSource.setValidationQuery(dsProperties.getValidationQuery());
        try {
            druidDataSource.setFilters(dsProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }
}
