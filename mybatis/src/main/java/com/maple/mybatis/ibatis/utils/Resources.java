package com.maple.mybatis.ibatis.utils;

import java.io.InputStream;

/**
 * @author 杨锋
 * @date 2022/10/13 21:23
 * desc:
 */

public class Resources {

    private Resources() {

    }


    public static InputStream getResourceAsStream(String name) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
    }
}
