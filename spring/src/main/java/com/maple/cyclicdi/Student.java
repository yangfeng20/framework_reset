package com.maple.cyclicdi;

/**
 * @author 杨锋
 * @date 2022/10/29 11:35
 * desc:
 */

public class Student {

    private Class clazz;

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "clazz=" + clazz.getClass() +
                '}';
    }
}
