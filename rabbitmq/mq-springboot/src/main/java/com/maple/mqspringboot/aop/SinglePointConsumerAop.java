package com.maple.mqspringboot.aop;

import com.maple.mqspringboot.config.ApolloConfig;
import com.rabbitmq.client.Channel;
import io.micrometer.core.instrument.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/11/8 21:15
 * desc:
 */


@ConditionalOnProperty
@Component
@Aspect
public class SinglePointConsumerAop {

    @Resource
    private ApolloConfig apolloConfig;


    @Around("execution(public void com..consumer(..)) && @annotation(rabbitListener)")
    public Object singlePointConsumer(ProceedingJoinPoint pjp, RabbitListener rabbitListener) {

        // 没有配置单点消费，跳过代理，直接消费
        if (StringUtils.isEmpty(apolloConfig.getPointConsumer())) {
            return directConsumer(pjp);
        }

        String consumerTag = null;
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Message) {
                consumerTag = ((Message) arg).getMessageProperties().getConsumerTag();
                break;
            }
        }
        // 配置单点消费，当前消费者tag与配置不相同，消息放回队列
        if (consumerTag != null && !consumerTag.equals(apolloConfig.getPointConsumer())) {
            return returnQueue(pjp.getArgs());
        }


        // 消费消息
        return directConsumer(pjp);
    }

    /**
     * 直接消费
     *
     * @param pjp pjp
     * @return {@link Object}
     */
    private Object directConsumer(ProceedingJoinPoint pjp) {
        Object returnVal = null;
        try {
            returnVal = pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return returnVal;
    }

    /**
     * 消息返回队列
     *
     * @param args arg游戏
     * @return {@link Object}
     */
    private Object returnQueue(Object[] args) {
        Channel channel = null;
        Long messageDeliveryTag = null;
        for (Object arg : args) {
            if (arg instanceof Message) {
                messageDeliveryTag = ((Message) arg).getMessageProperties().getDeliveryTag();
            }

            if (arg instanceof Channel) {
                channel = (Channel) arg;
            }
        }

        // 重新放回队列
        if (channel != null && messageDeliveryTag != null) {
            try {
                Thread.sleep(2000);
                channel.basicNack(messageDeliveryTag, false, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
