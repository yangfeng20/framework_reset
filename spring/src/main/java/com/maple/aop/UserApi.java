package com.maple.aop;

import org.springframework.stereotype.Controller;

/**
 * @author 杨锋
 * @date 2022/10/30 11:21
 * desc:
 */

@Controller
public class UserApi implements UserInter{

    @FillHeader
    public User queryUser() {
        System.out.println("我是查询用户");
        return new User();
    }

    @Override
    public void test01(Integer a) {
        System.out.println("UserApi.test01");
    }
}
