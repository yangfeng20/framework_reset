package com.maple.instance;

/**
 * @author 杨锋
 * @date 2022/10/29 10:20
 * desc:
 */

public class StudentFactory {

    public static StudentFactory getInstance() {
        return new StudentFactory();
    }
}
