package com.maple.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 杨锋
 * @date 2022/11/10 11:09
 * desc:
 */

@ComponentScan("com.maple.postprocessor")
public class BeanFactoryPostProcessorDemo {
    public static void main(String[] args) {
        // VM参数：-Dconsumer=dev
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorDemo.class);

        try {
            Object student = applicationContext.getBean("student");
            System.out.println(student);
        } catch (BeansException e) {
            System.out.println("没有bean：student");
        }
    }
}
