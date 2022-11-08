package com.maple.mqspringboot;

import com.maple.mqspringboot.config.RabbitMqConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/11/8 17:04
 * desc:
 */


@RestController
public class MessageSenderController {


    @Resource
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/send/message/{msg}", method = RequestMethod.GET)
    public String senderMessage(@PathVariable String msg) {

        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        amqpTemplate.convertAndSend(RabbitMqConst.EXCHANGE, RabbitMqConst.KEY, msg + " " + dateStr);
        return dateStr;
    }


    /**
     * 发送消息优先级
     *
     * @param msg 味精
     * @return {@link String}
     */
    @RequestMapping(value = "/send/message/{msg}/{priority}", method = RequestMethod.GET)
    public String senderMessagePriority(@PathVariable String msg, @PathVariable Integer priority) {

        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Message message = MessageBuilder
                .withBody((msg + " " + dateStr).getBytes(StandardCharsets.UTF_8))
                .setPriority(priority)
                .build();
        amqpTemplate.convertAndSend(RabbitMqConst.EXCHANGE, RabbitMqConst.KEY, message);
        return dateStr;
    }
}
