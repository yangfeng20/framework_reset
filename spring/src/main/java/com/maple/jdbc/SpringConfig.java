package com.maple.jdbc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author 杨锋
 * @date 2022/10/29 17:39
 * desc: spring配置，自定义连接池
 */

@Configuration
@PropertySource(value = "classpath:setter/jdbc.properties")
@ComponentScan("com.maple.jdbc")
public class SpringConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(applicationContext.getBean("dataSource", DataSource.class));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
