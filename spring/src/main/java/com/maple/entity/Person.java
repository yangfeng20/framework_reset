package com.maple.entity;

/**
 * @author 杨锋
 * @date 2022/10/28 20:50
 * desc:
 */

public class Person {

    private String name;

    private User user;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }

    public Person(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
