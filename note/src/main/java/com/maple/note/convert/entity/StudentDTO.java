package com.maple.note.convert.entity;

import lombok.Data;

/**
 * @author 杨锋
 * @date 2022/11/26 9:18
 * desc:
 */

@Data
public class StudentDTO {


    private Long id;

    private String name;


    private Integer age;


    private UserInfo createId;
}
