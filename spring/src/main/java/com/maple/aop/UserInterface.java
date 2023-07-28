package com.maple.aop;

/**
 * @author yangfeng
 * @date : 2023/7/27 16:45
 * desc:
 */

public interface UserInterface {


    void test01(Integer a);


    default boolean test02(Integer a, Object b, int[] d) {
        System.out.println("test02");
        return true;
    }
}
