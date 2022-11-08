package com.maple.mqspringboot.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

/**
 * @author 杨锋
 * @date 2022/11/8 17:01
 * desc:
 */

//@Configuration
public class MqQueueConfig {


    @Bean
    public Queue priorityQueue(){
        // 同时发送消息是也需要设置properties
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();


        // x-max-priority参数的值应该介于1到255。建议使用1到10之间的队列。
        HashMap<String, Object> map = new HashMap<>();
        // 队列开启优先级，最大10，可设置255
        map.put("x-max-priority", 10);
        return new Queue("priorityQueue", false, false, true, map);
    }


}
