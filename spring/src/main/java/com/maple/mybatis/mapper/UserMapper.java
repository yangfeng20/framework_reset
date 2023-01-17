package com.maple.mybatis.mapper;

import com.maple.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:07
 * desc:
 */

//@Repository
public interface UserMapper {


    /**
     * 选择用户
     *
     * @return {@link User}
     */
    @Select("select * from person limit 1")
    User selectUser();
}
