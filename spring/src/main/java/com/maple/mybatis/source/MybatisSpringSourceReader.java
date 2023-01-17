package com.maple.mybatis.source;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Set;

/**
 * @author yangfeng
 * @date : 2023/1/17 22:33
 * desc:
 */



/**
 * note MapperScan这个里面是关键代码
 * note 已经FactoryBean【MapperFactoryBean】
 * 获取mapper {@link MapperFactoryBean}
 * 扫描之后注册 {@link ClassPathMapperScanner#processBeanDefinitions(Set)}
 */
@MapperScan("com.maple.mybatis.mapper")
@ComponentScan("com.maple.mybatis.source")
public class MybatisSpringSourceReader {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();


    }
}
