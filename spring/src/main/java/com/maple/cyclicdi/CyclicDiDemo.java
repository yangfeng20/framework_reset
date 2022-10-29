package com.maple.cyclicdi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/29 11:33
 * desc: 循环依赖
 */

public class CyclicDiDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cyclicdi/cyclicdi.xml");

        System.out.println(applicationContext.getBean("student"));
        System.out.println(applicationContext.getBean("clazz"));
    }
}
