package com.maple.myspring.entity;

import lombok.Setter;
import lombok.ToString;

/**
 * @author 杨锋
 * @date 2022/10/29 14:40
 * desc:
 */

@Setter
@ToString
public class User {

    private Student student;

    private String name;

    private Integer age;

    private Boolean test;
}
