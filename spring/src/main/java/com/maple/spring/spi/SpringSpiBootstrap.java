package com.maple.spring.spi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/12/18 16:12
 * desc:
 */



public class SpringSpiBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringSpiBootstrap.class);

        // 根据spring.factories动态加载实现类【其实应该是两个项目，接口是一个，使用接口的是另外一个】
        List<FormatInterface> loadFactories = SpringFactoriesLoader.loadFactories(FormatInterface.class, null);
        System.out.println(loadFactories);
    }
}
