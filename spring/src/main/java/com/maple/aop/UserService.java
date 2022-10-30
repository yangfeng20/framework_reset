package com.maple.aop;

import org.springframework.stereotype.Service;

/**
 * @author 杨锋
 * @date 2022/10/29 21:15
 * desc:
 */


@Service
public class UserService {




    public User createUser(UserParam userParam){
        System.out.println("我在创建用户");

        return new User();
    }

    public User queryUser(Long id){
        System.out.println("我在查询用户");
        return new User();
    }

    public boolean updateUser(UserParam user){
        System.out.println("我在更新用户");
        return true;
    }
}
