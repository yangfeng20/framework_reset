package com.maple.setter.entity;

import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/28 23:05
 * desc:
 */

@Setter
@ToString
public class Student {

    private String name;

    private Map<String,String> properties;

    private List<String> list;
}
