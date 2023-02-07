package com.maple.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;

/**
 * @author 杨锋
 * @date 2022/10/29 10:54
 * desc:
 */

public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean,DisposableBean {

    private String name;

    public User() {
        System.out.println("1：构造方法");
    }

    public void setName(String name){
        this.name = name;
        System.out.println("2：set方法执行");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("x: User.postConstruct");
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("3：检查实现aware接口并调用方法-->"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3：检查实现aware接口并调用方法-->"+beanFactory);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3：检查实现aware接口并调用方法-->"+s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5：检查实现InitializingBean接口并调用方法");
    }

    public void initBean(){
        System.out.println("6：bean自定义init方法");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("9：检查实现DisposableBean接口并调用方法");
    }

    public void destroyBean(){
        System.out.println("10：bean自定义destroyBean方法");
    }
}
