package com.maple.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/29 10:54
 * desc: bean的生命周期（不包含beanDefine对象）
 */

@SuppressWarnings("all")
public class BeanLifecycleDemo {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext  applicationContext = new ClassPathXmlApplicationContext("lifecycle/lifecycle.xml");

        // bean对象的完整生命周期只有单例对象有，原型模式没有销毁的生命周期（最后两步）
        User user = applicationContext.getBean("user", User.class);
        User user1 = applicationContext.getBean("user", User.class);
        Thread.sleep(1000);
        System.out.println("8：使用bean"+user);


        // 关闭容器
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
