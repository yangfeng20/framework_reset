package com.maple.transaction.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨锋
 * @date 2022/10/30 13:46
 * desc:
 */

@Getter
@Configuration
public class JdbcProperties {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;
}
