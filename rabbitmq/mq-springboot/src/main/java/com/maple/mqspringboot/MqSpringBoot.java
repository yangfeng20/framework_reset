package com.maple.mqspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/11/8 16:35
 * desc:
 */


@SpringBootApplication
public class MqSpringBoot {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MqSpringBoot.class, args);

        Date date = applicationContext.getBean("date", Date.class);
        System.out.println(date);
    }


}
