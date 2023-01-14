package com.maple.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 杨锋
 * @date 2023/1/8 22:00
 * desc:
 */


@ComponentScan
public class SpringTestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringTestMain.class);

        SpringTestMain bean = applicationContext.getBean(SpringTestMain.class);

        System.out.println(bean);
    }
}
