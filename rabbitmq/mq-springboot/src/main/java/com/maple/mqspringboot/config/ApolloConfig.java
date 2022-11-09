package com.maple.mqspringboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨锋
 * @date 2022/11/9 7:33
 * desc:
 */

@Getter
@Setter
@Configuration
public class ApolloConfig {


    @Value("${pointConsumer}")
    private String pointConsumer;
}
