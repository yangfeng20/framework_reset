package com.maple.mybatis.mapper;

import com.maple.mybatis.entity.Person;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangfeng
 * @date : 2023/1/17 21:40
 * desc:
 */

//@Repository
public interface PersonMapper {


    /**
     * 选择人
     *
     * @return {@link Person}
     */
    @Select("select * from person limit 1")
    Person selectPerson();
}
