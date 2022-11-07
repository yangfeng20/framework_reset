package com.maple.note.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 杨锋
 * @date 2022/11/7 20:39
 * desc:
 */

public class MqConsumer01 {
    public static void main(String[] args) throws Exception {
        accept();
    }

    public static void accept() throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(MqConstant.QUEUE,false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 接收消息
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("接收的消息为："+message);

                // ack
                Channel innerChanel = super.getChannel();
                innerChanel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
