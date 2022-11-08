package com.maple.mqspringboot;

import com.maple.mqspringboot.config.RabbitMqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;

/**
 * @author 杨锋
 * @date 2022/11/8 17:25
 * desc:
 */


@Component
public class MqConsumer {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = RabbitMqConst.QUEUE, durable = "false"),
                    exchange = @Exchange(value = RabbitMqConst.EXCHANGE, type = ExchangeTypes.TOPIC),
                    key = RabbitMqConst.KEY)},
            ackMode = "MANUAL"
    )
    public void consumer(@Payload String msg, Channel channel, Message message) {
        System.out.println("接收到消息：" + msg+"\n");

        StopWatch stopWatch = new StopWatch("MqConsumer");
        stopWatch.start("step1");
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            stopWatch.stop();
        } catch (Exception e) {
            stopWatch.start("step2");
            try {
                Thread.sleep(5000);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        } finally {
            String prettyPrint = stopWatch.prettyPrint();
            System.out.println(prettyPrint);
        }
    }
}
