package com.maple.app;

import java.util.Date;

/**
 * @author yangfeng
 * @date : 2023/7/31 10:13
 * desc:
 */

public class AppDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("-------------------main start---------------");
        System.out.println("hello world");
        System.out.println("-------------------main end-----------------");
        Thread.sleep(1000000000000L);


        Class<Student> clazz = Student.class;
        Date date = new Date();
        System.out.println("类：" + clazz);
        System.out.println("实例：" + clazz.newInstance());
    }
}
