package com.maple.mqspringboot;

import com.maple.mqspringboot.config.RabbitMqConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        amqpTemplate.convertAndSend(RabbitMqConst.EXCHANGE, "dddddd", msg + " " + dateStr);
        return dateStr;
    }
}
