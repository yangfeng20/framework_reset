package ack;

import com.rabbitmq.client.*;
import quick.MqConnectionUtil;
import quick.MqConstant;

import java.io.IOException;

/**
 * @author 杨锋
 * @date 2022/11/8 11:35
 * desc:
 */

public class ConsumerAck {
    public static void main(String[] args) throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();


        // 监听队列
        channel.basicConsume(MqConstant.QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    System.out.println("正在处理消息：" + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (IOException e) {
                    // 延时一段时间丢回队列
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
    }
}
