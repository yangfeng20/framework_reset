package com.maple.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/11/19 10:19
 * desc:
 */

@ComponentScan("com.maple.event")
public class EventDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(EventDemo.class);

        EventSource eventSource = new EventSource(new Date());

        System.out.println("开始发送事件");
        applicationContext.publishEvent(eventSource);
        System.out.println("发送事件结束");


        //EventPublish eventPublish = applicationContext.getBean("eventPublish", EventPublish.class);
        //eventPublish.publisher(eventSource);
    }
}
