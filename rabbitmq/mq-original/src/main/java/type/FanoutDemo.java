package type;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import quick.MqConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author 杨锋
 * @date 2022/11/8 15:25
 * desc:
 */

public class FanoutDemo {
    public static void main(String[] args) throws Exception{
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();



        // fanout （扇出交换机）就算写了routingKey也不会使用，而是直接绑定了就会广播到绑定的交换机
        channel.basicPublish("fanout_exchange", "eeee", null, "fanout——message".getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();
    }
}
