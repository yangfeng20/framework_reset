package com.maple.note.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/21 19:08
 * desc:
 */

@Data
public class Student01 {

    private String name;


    private List<Address01> addressList;

    private Integer age;
}
