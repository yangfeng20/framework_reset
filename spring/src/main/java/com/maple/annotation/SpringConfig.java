package com.maple.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨锋
 * @date 2022/10/29 17:23
 * desc:
 */

@Configuration
@ComponentScan("com.maple.annotation")
public class SpringConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
