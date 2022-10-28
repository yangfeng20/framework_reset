package com.maple.setter;

import com.maple.setter.entity.Person;
import com.maple.setter.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/28 20:54
 * desc: set注入
 */

public class SetterInjectDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("setter.xml");

        // setter注入
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);

        // autowire自动装配
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);



    }
}
