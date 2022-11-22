package com.maple.mqspringboot;

import lombok.Data;

/**
 * @author 杨锋
 * @date 2022/11/21 20:11
 * desc:
 */

@Data
@Consumer
public class Student {

    private String name;

    private Long id;
}
