package com.maple.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author 杨锋
 * @date 2022/10/29 19:12
 * desc:
 */

@Configuration
@PropertySource(value = "classpath:setter/jdbc.properties")
@ComponentScan("com.maple.jdbc")
public class SpringDruidConfig {

    /**
     * 使用德鲁伊连接池
     *
     * @return {@link DataSource}
     */
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);

        return druidDataSource;
    }

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;
}
