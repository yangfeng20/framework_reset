package com.maple.note.test;

import com.maple.smart.config.core.annotation.JsonValue;
import com.maple.smart.config.core.annotation.SmartValue;

/**
 * @author maple
 * Created Date: 2024/3/27 16:00
 * Description:
 */

public class SmartConfig {

    @JsonValue("${aaa:{}}")
    private static Object name;

    @SmartValue("${value1:111}")
    private String value1;

    @SmartValue("${value2}")
    private static String value2;

    public void test01() {
        System.out.println("======================================================");
        System.out.println("name = " + name);
        System.out.println("value1 = " + value1);
        System.out.println("value2 = " + value2);
        System.out.println("======================================================");
    }
}
