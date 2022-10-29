package com.maple.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;

/**
 * @author 杨锋
 * @date 2022/10/29 15:29
 * desc: spring基于注解开发
 */

public class AnnotationDemo {
    public static void main1(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation/annotation.xml");
        // 没有名称，默认简单类名加首字母小写
        UserService userService = applicationContext.getBean("userService", UserService.class);
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        System.out.println(userService);
        System.out.println(userDao);


        // todo 没有setter方法时可以通过字段反射赋值
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user + " " + user.hashCode());
        Class<? extends User> userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(user, "李四");
        System.out.println(user + " " + user.hashCode());
    }

    public static void main2(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation/annotation.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);

        UserController userController = applicationContext.getBean("userController", UserController.class);
        System.out.println(userController);
    }

    public static void main(String[] args) {
        // todo 直接指定扫描路径 或者使用配置类
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.maple.annotation");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(applicationContext.getBean("userService"));

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }
}
