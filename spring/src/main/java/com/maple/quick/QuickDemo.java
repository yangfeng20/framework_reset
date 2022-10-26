package com.maple.quick;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/26 9:54
 * desc:
 */

public class QuickDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        Object bean = applicationContext.getBean("userBean");
        System.out.println(bean);
    }
}
