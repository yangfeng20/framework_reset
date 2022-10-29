package com.maple.myspring;

/**
 * @author 杨锋
 * @date 2022/10/29 14:06
 * desc:
 */

public interface ApplicationContext {


    /**
     * 让豆
     *
     * @param name 名字
     * @return {@link Object}
     */
    Object getBean(String name);


    /**
     * 让豆
     *
     * @param name  名字
     * @param clazz clazz
     * @return {@link T}
     */
    <T> T getBean(String name, Class<T> clazz);
}
