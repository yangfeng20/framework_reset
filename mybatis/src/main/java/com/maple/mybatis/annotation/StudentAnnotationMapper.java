package com.maple.mybatis.annotation;

import com.maple.mybatis.table.mapping.entity.Student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/16 11:41
 * desc:
 */

public interface StudentAnnotationMapper {


    @Select("select * from student01")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "homeAddress", column = "home_address")
    })
    List<Student> selectAll();
}
