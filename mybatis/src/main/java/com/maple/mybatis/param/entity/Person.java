package com.maple.mybatis.param.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (Person)表实体类
 *
 * @author makejava
 * @since 2022-10-14 23:17:16
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private Long id;
    
    private String name;

    /**
     * 家庭住址
     */
    private String homeAddress;
    
    private Long accountId;
    
    private Date createdDate;
    
    private Date updatedDate;
    
}


