package com.maple.annotation;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 杨锋
 * @date 2022/10/29 16:38
 * desc:
 */

@Service
@ToString
public class UserService {


    private UserDao userDao;


    /**
     * 默认byType，有多个对象时报错
     * @param userDao
     */
    @Qualifier("mysqlDao")
    @Autowired
    public void setUserDao(UserDao userDao) {
        System.out.println("UserService.setUserDao");
        this.userDao = userDao;
    }
}
