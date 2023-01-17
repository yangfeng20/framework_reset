package com.maple.test.main.config;

import com.maple.test.main.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangfeng
 * @date : 2023/1/15 16:34
 * desc:
 */

@Configuration
public class RegistryConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
