package com.maple.mqspringboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/11/8 21:15
 * desc:
 */


@Component
@Aspect
public class SinglePointConsumerAop {


    @Before("execution(public void com..consumer(..)) && @annotation(rabbitListener)")
    public void singlePointConsumer(JoinPoint joinPoint, RabbitListener rabbitListener){
        Object[] args = joinPoint.getArgs();
        System.out.println("注解切面-----");
    }


}
