package com.maple.mybatis;

import com.maple.mybatis.ann.MapleMapperScanner;
import com.maple.mybatis.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangfeng
 * @date : 2023/1/17 19:54
 * desc:
 */


@ComponentScan("com.maple.mybatis")
@MapleMapperScanner("com.maple.mybatis.mapper")
public class MybatisSpringBootstrap {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisSpringBootstrap.class);

        // 使用
        UserService userService = applicationContext.getBean(UserService.class);
        userService.queryUser();
        userService.queryPerson();

    }
}

