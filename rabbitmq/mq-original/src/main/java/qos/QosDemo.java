package qos;

import com.rabbitmq.client.*;
import quick.MqConnectionUtil;
import quick.MqConstant;

import java.io.IOException;

/**
 * @author 杨锋
 * @date 2022/11/8 12:50
 * desc:
 */

public class QosDemo {
    public static void main(String[] args) throws Exception{
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 消费者还未处理完消息，能够给到的最大消息数量
        channel.basicQos(2, false);


        channel.basicConsume(MqConstant.QUEUE, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("正在处理消息："+new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
