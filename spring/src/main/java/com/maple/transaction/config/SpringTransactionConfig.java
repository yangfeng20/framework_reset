package com.maple.transaction.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 杨锋
 * @date 2022/10/30 13:43
 * desc:
 */

@Configuration
// 开启事务注解管理器
@EnableTransactionManagement
@PropertySource("classpath:setter/jdbc.properties")
@ComponentScan("com.maple.transaction")
public class SpringTransactionConfig implements ApplicationContextAware {

    /**
     * 可以不需要容器对象，通过@Bean创建对象，对自动注入需要的对象，如果容器中有的话
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(applicationContext.getBean("dataSource", DataSource.class));
    }

    @Bean
    public DataSource dataSource() {
        JdbcProperties jdbcProperties = applicationContext.getBean("jdbcProperties", JdbcProperties.class);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcProperties.getUrl());
        druidDataSource.setPassword(jdbcProperties.getPassword());
        druidDataSource.setDriverClassName(jdbcProperties.getDriver());
        druidDataSource.setUsername(jdbcProperties.getUsername());
        return druidDataSource;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }



}
