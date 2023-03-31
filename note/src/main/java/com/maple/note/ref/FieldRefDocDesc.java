package com.maple.note.ref;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * @author yangfeng
 * @date : 2023/3/7 23:20
 * desc:
 */

public class FieldRefDocDesc {
    public static void main(String[] args) {
        Class<Student> clazz = Student.class;
        Field[] fields = ReflectUtil.getFields(clazz);
        for (Field field : fields) {
            System.out.println(field);
        }
    }


    static class Student{
        /**
         * 名称
         */
        private String name;

        /**
         * 年龄
         */
        private String age;
    }

}

