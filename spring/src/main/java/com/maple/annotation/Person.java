package com.maple.annotation;

import lombok.ToString;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/10/29 17:27
 * desc:
 */


@ToString
public class Person {

    @Resource
    private UserController userController;
}
