package com.maple.note.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 杨锋
 * @date 2022/11/7 20:14
 * desc:
 */

public class MqConnectionUtil {


    public static Connection getConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("mq-common.qa5.yqn.corp");
        connectionFactory.setPassword("rdyed9ELwN3xSvEs7r");
        connectionFactory.setUsername("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        assert connection != null;
        return connection;
    }
}
