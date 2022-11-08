package ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import quick.MqConnectionUtil;
import quick.MqConstant;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author 杨锋
 * @date 2022/11/8 14:20
 * desc: ttl过期时间 （ttl过期时间以及死信队列使用）
 * 通过ttl+死信队列可以实现延迟队列，也就是说延迟队列也就是死信队列
 *
 * 消息属性设置TTL： 因为通过消息属性设置TTL，发送到队列的消息是各不相同的，如果你想要判断消息是否过期，那么就要扫描整个队列，所以索性等到消息即将要发送给消费者时（队列头），判断消息是否过期，如果消息过期则抛弃或者进入死信队列。
 * todo 也就是说这种消息，只有在队列头的时候才会去检查消息是否过期，如果后面的消息已经过期了，不能及时的丢弃到死信队列中，那么其实就是有问题的，时间不准确。
 * 队列设置消息TTL： 因为通过队列设置消息TTL，整个队列中的过期时间是一样的（不另外通过消息属性设置TTL），那么过期的消息势必出现在队列头一整段，这个时候从队列头扫描（定时扫描）到没有过期的消息前，将这一段的消息全部抛弃或者进入死信队列。
 * todo 这种可以正常的原因是，队列中所有的过期时间都是一样的，那么队列头的消息是肯定大于后面的过期时间的（队列头先过期），就只需要从队列头扫描过期的消息即可
 */

public class TtlDeadDemo {
    public static void main(String[] args) throws Exception{
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();



        HashMap<String, Object> map = new HashMap<>();
        // 10秒之后消息死亡(单位毫秒)
        map.put("x-message-ttl", 10000);
        // 队列到时间自动过期销毁
        map.put("x-expires", 1000000000);
        // todo 设置当前队列的死信交换机(也就是消息过期之后，自动发送的指定的交换机)
        map.put("x-dead-letter-exchange", MqConstant.EXCHANGE);
        map.put("x-dead-letter-routing-key", MqConstant.KEY);

        AMQP.Queue.DeleteOk queueDelete = channel.queueDelete("ttl_queue");
        channel.queueDeclare("ttl_queue", false, false, true, map);
        channel.queueBind("ttl_queue", MqConstant.EXCHANGE, "ttl");

        for (int i = 0; i < 50; i++) {
            channel.basicPublish(MqConstant.EXCHANGE, "ttl", null, ("自动过期的消息 : "+ i).getBytes(StandardCharsets.UTF_8));
        }

        Thread.sleep(5000);
        for (int i = 50; i < 100; i++) {
            channel.basicPublish(MqConstant.EXCHANGE, "ttl", null, ("自动过期的消息 : "+ i).getBytes(StandardCharsets.UTF_8));
        }


        channel.close();
        connection.close();
    }
}
