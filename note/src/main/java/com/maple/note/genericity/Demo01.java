package com.maple.note.genericity;

/**
 * @author 杨锋
 * @date 2022/11/1 16:18
 * desc:
 */

public class Demo01 {
    public static void main(String[] args) {
        Student<String> student = new Student<>();

    }


    static class Student<T>{
        private T data;
    }
}
