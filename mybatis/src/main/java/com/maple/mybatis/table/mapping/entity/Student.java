package com.maple.mybatis.table.mapping.entity;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/15 10:01
 * desc:
 */


@ToString
public class Student implements Serializable {

    private Long id;

    private String name;
    private Integer age;
    private Date createdDate;

    private Clazz clazz;
}

