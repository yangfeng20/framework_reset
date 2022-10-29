package com.maple.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/29 10:54
 * desc: bean的生命周期（不包含beanDefine对象）
 */

@SuppressWarnings("all")
public class BeanLifecycleDemo {
    public static void main(String[] args) throws Exception{
        ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("lifecycle/lifecycle.xml");

        // bean对象的完整生命周期只有单例对象有，原型模式没有销毁的生命周期（最后两步）
        User user = applicationContext.getBean("user", User.class);
        System.out.println("8：使用bean"+user);

        // todo 自己创建的对象给spring管理
        Person person = new Person();
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("person", person);

        new Thread(()->{
            System.out.println(beanFactory.getBean("person"));
        }).start();


        Thread.sleep(1000);
        // 关闭容器
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
