package com.maple.note.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/11/7 20:19
 * desc:
 */

public class MqProvider {

    public static void main(String[] args) throws Exception {
        sendMessage();
    }

    public static void sendMessage() throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(MqConstant.QUEUE, false, false, false, null);
        // 声明交换机
        channel.exchangeDeclare(MqConstant.EXCHANGE, "topic", true, false, null);
        // 绑定队列
        channel.queueBind(MqConstant.QUEUE, MqConstant.EXCHANGE, MqConstant.KEY);

        String message = System.getProperty("username") + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // 发布消息到交换机
        channel.basicPublish(MqConstant.EXCHANGE, MqConstant.KEY, null, message.getBytes(StandardCharsets.UTF_8));

        // 关闭连接
        channel.close();
        connection.close();
    }
}
