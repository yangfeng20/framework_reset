package com.maple.mqspringboot.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * mq连接配置
 *
 * @author 杨锋
 * @date 2022/11/8 16:41
 * desc:使用此配置就不用读取默认的配置了，需要自己实现
 */


//@Configuration
public class MqConnectionConfig {


    @Bean("baseRabbitTemplate")
    public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("mq-common.qa5.yqn.corp");
        connectionFactory.setPassword("rdyed9ELwN3xSvEs7r");
        connectionFactory.setUsername("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        return connectionFactory;
    }
}
