package type;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import quick.MqConnectionUtil;

import java.io.IOException;

/**
 * @author 杨锋
 * @date 2022/11/8 15:30
 * desc:
 */

public class FanoutConsumer {
    public static void main(String[] args) throws Exception{
        Channel channel = MqConnectionUtil.getConnection().createChannel();

        String queueName = channel.queueDeclare().getQueue();

        channel.exchangeDeclare("fanout_exchange", "fanout", false, true, null);
        channel.queueBind(queueName, "fanout_exchange", "abc");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收消息："+new String(body));
            }
        });
    }
}
