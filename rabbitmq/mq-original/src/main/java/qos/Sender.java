package qos;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import quick.MqConnectionUtil;
import quick.MqConstant;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author 杨锋
 * @date 2022/11/8 10:35
 * desc: 发送确认（消息发送到交换机回调）
 * todo 以下的确认都是到交换机就会确认
 */

public class Sender {
    public static void main(String[] args) throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();


        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入消息：");
            String msg = scanner.next();
            String message = System.getProperty("user.name") + " 发送消息：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            message = message + msg;
            // 发布消息到交换机
            channel.basicPublish(MqConstant.EXCHANGE, MqConstant.KEY, null, message.getBytes(StandardCharsets.UTF_8));
        }

        //channel.close();
        //connection.close();
    }
}
