package com.maple.mybatis.service;

import com.maple.mybatis.entity.Person;
import com.maple.mybatis.entity.User;
import com.maple.mybatis.mapper.PersonMapper;
import com.maple.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yangfeng
 * @date : 2023/1/17 20:10
 * desc:
 */


@Component
public class UserService {

    @Resource
    private UserMapper userMapper;


    @Resource
    private PersonMapper personMapper;


    public User queryUser() {
        return userMapper.selectUser();
    }

    public Person queryPerson() {
        return personMapper.selectPerson();
    }
}
