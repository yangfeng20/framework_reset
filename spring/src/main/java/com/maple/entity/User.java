package com.maple.entity;

/**
 * @author 杨锋
 * @date 2022/10/26 9:56
 * desc:
 */

public class User {

    public User(){
        System.out.println("User.User");
    }

    private Student student;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
