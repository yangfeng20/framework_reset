package com.maple.mybatis.table.mapping.entity;

import lombok.ToString;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/15 10:01
 * desc:
 */


@ToString
public class Student {

    private Long id;

    private String name;
    private Integer age;
    private Date createdDate;

    private Clazz clazz;
}

