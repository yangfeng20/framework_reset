package com.maple.aop.my.service;

/**
 * @author 杨锋
 * @date 2022/11/19 19:43
 * desc:
 */

public class StudentServiceImpl implements StudentService {
    @Override
    public void addStudent() {
        System.out.println("StudentServiceImpl.addStudent");
    }

    @Override
    public void deleteStudent() {
        System.out.println("StudentServiceImpl.deleteStudent");
    }
}
