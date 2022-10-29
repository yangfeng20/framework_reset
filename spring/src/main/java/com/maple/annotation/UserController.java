package com.maple.annotation;

import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/10/29 17:13
 * desc:
 */

@Controller
@ToString
public class UserController {

    /**
     * 默认byName，没有指定name是，通过属性名找；通过byName找不到时，通过byType方式
     */

    private UserService userService;


    @Resource
    public void setUserService(UserService userService) {
        System.out.println("UserController.setUserService");
        this.userService = userService;
    }
}
