package com.maple.mybatis.table.mapping.entity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/15 10:03
 * desc:
 */

public interface StudentMapper {

    Student selectById(Long id);


    List<Student> selectPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<Student> selectPageHelper();
}
