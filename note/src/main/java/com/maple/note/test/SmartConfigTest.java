package com.maple.note.test;

import com.maple.smart.config.core.boot.AbsConfigBootstrap;
import com.maple.smart.config.core.boot.LocalConfigBootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maple
 * Created Date: 2024/3/27 15:59
 * Description:
 */


public class SmartConfigTest {

    public static void main(String[] args) throws Exception {

        // 调试jar包
        Thread.sleep(3000);

        List<String> list = new ArrayList<>();
        list.add("com.maple.note.test");
        AbsConfigBootstrap bootstrap = new LocalConfigBootstrap(true, 6767,
                "classpath:application.properties", list);
        bootstrap.init();

        SmartConfig service = new SmartConfig();

        for (int i = 0; i < 1000; i++) {
            service.test01();
            Thread.sleep(10000);
        }

    }
}
