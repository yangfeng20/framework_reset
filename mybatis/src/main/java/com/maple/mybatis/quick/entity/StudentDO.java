package com.maple.mybatis.quick.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Student)表实体类
 *
 * @author makejava
 * @since 2022-10-13 16:56:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO implements Serializable {

    /**
     * 自增主键
     */
    private Long id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 创建时间
     */
    private Date createdDate;
    
    /**
     * 班级id
     */
    private Long classId;
    
    private Boolean test;
    
}


