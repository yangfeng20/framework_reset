package com.maple.note.convert.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author 杨锋
 * @date 2022/11/26 9:17
 * desc:
 */


@Data
@Builder
public class StudentDO {

    private Long id;

    private String name;


    private Integer age;


    private Long createId;
}
