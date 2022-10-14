package com.maple.mybatis.param.entity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/14 23:21
 * desc:
 */

public interface PersonMapper {

    /**
     * 插入人
     *
     * @param person 人
     */
    void insertPerson( Person person);


    List<Person> selectList(@Param("name") String name, @Param("id") Long id);


    Person selectOne(Long id);

}
