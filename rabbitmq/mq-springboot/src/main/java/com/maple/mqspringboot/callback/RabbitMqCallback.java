package com.maple.mqspringboot.callback;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/11/8 19:28
 * desc:
 */


@Component
public class RabbitMqCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }


    /**
     * 需要配置并读取默认配置：spring.rabbitmq.publisher-confirm-type=correlated
     *
     * @param correlationData 关联数据
     * @param ack             消
     * @param cause           导致
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息发送到交换机结果：" + ack);
        if (cause != null) {
            System.out.println("失败原因：" + cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("消息未到达queue,key：" + returned.getRoutingKey()  + " 原因是："+returned.getReplyText());
    }
}
