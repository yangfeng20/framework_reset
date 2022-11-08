package confirm;

import com.rabbitmq.client.*;
import quick.MqConnectionUtil;
import quick.MqConstant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 杨锋
 * @date 2022/11/8 10:35
 * desc: 发送确认（消息发送到交换机回调）
 * todo 以下的确认都是到交换机就会确认
 */

public class SenderConfirmCallback {
    public static void main(String[] args) throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 开启发布确认
        channel.confirmSelect();


        // 异步回调确认(同样也是到达交换机之后就被回调) ConfirmCallback ackCallback, ConfirmCallback nackCallback (确认ack回调，nack回调)
        channel.addConfirmListener(new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag + " 消息被ack");
            }
        }, new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                // 未被成功确认的消息可以重新发送，使用队列或者map重新拿到消息
                System.out.println(deliveryTag + " 消息被nack");
            }
        });


        // todo 消息推送到队列失败回调确认(exchange路由到queue失败回调)
        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return returnMessage) {
                System.out.println("消息推送到queue失败："+new String(returnMessage.getBody()));
            }
        });

        // 发布者等待确认 todo 需要添加mandatory，returnLister才会生效
        channel.basicPublish(MqConstant.EXCHANGE, MqConstant.KEY, true, null, "confirm".getBytes(StandardCharsets.UTF_8));
        if (channel.waitForConfirms()) {
            System.out.println("成功发送到交换机");
        } else {
            System.out.println("发送到交换机失败");
        }


        //channel.close();
        //connection.close();
    }
}
