package com.maple.note.collection;

import java.util.HashMap;

/**
 * @author 杨锋
 * @date 2022/10/30 17:36
 * desc:
 */

public class DemoMain {
    public static void main(String[] args) {

        HashMap<Student, Integer> map = new HashMap<>();
        Student student = new Student();
        student.setAge(10);
        map.put(student, 1);

        student.setAge(20);

        Integer result = map.get(student);
        System.out.println(result);
    }
}
