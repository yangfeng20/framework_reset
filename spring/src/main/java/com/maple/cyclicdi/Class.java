package com.maple.cyclicdi;

/**
 * @author 杨锋
 * @date 2022/10/29 11:35
 * desc:
 */

public class Class {

    private Student student;

    @Override
    public String toString() {
        return "Class{" +
                "student=" + student.getClass() +
                '}';
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
