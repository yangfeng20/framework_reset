package com.maple.mybatis.mvc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/14 11:48
 * desc:
 */


@Data
@Accessors(chain = true)
public class Account {

    private Long id;

    private String name;

    private Integer balance;

    private Date createdDate;
}
