package com.maple.instance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

/**
 * @author 杨锋
 * @date 2022/10/29 10:18
 * desc:
 */

public class BeanInstanceDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("instance/instance.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);

        StudentFactory studentFactory = applicationContext.getBean("studentFactory", StudentFactory.class);
        System.out.println(studentFactory);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ss");
        System.out.println(simpleDateFormat.format(applicationContext.getBean("myDate")));
    }
}
