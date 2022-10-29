package com.maple.annotation;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/10/29 16:52
 * desc:
 */

@Component
@ToString
public class User {

    /**
     * value可以卸载字段，构造方法形参，setter方法上；没有setter方法也可以注入，通过字段反射.set()方法赋值
     * value写在setter方法上，会调用setter方法，
     */
    @Value("张三")
    private String name;


    private Integer age;

    public void setName(String name) {
        System.out.println("setter执行");
        this.name = name;
    }

    @Value("10")
    public void setAge(Integer age) {
        System.out.println("User.setAge");
        this.age = age;
    }
}
