package com.maple.condition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 杨锋
 * @date 2022/11/10 9:32
 * desc: 条件注入演示
 */

public class ConditionInjectDemo {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionBootStrapConfig.class);
        System.out.println(applicationContext.getBean("student"));
    }
}
