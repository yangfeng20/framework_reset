package com.maple.note.base;

/**
 * @author 杨锋
 * @date 2022/11/12 9:06
 * desc:
 */

public class IsClassEquals {
    public static void main(String[] args) {
        // 前面是父类型，后面的参数是子类型，满足条件返回true
        System.out.println(Person.class.isAssignableFrom(Student.class));
        System.out.println(Student.class.isAssignableFrom(Person.class));
        System.out.println(Object.class.isAssignableFrom(Student.class));
        System.out.println(Object.class.isAssignableFrom(Person.class));
    }


    static class Person{

    }

    static class Student extends Person{

    }


}
