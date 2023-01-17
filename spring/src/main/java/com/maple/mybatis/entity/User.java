package com.maple.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:08
 * desc:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;
}
