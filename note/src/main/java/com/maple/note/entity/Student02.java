package com.maple.note.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/21 19:08
 * desc:
 */

@Data
public class Student02 {

    private String name;


    private List<Address02> addressList;
}
